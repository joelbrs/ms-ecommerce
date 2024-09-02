package tech.joelf.ms_product.queues;

import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import tech.joelf.ms_product.model.ProductCategory;
import tech.joelf.ms_product.services.ProductCategoryService;

@Component
public class ProductCategorySubscriber {

    private final ProductCategoryService productCategoryService;

    public ProductCategorySubscriber(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @RabbitListener(queues = "${amqp.exchange.relations.product-category.name}")
    public void receiveProductCategory(@Payload String message) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            List<ProductCategory> productCategories = mapper.readValue(message,
                    new TypeReference<List<ProductCategory>>() {
                    });

            productCategoryService.save(productCategories);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
