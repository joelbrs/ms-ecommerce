package tech.joelf.ms_relations.queues;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import tech.joelf.ms_relations.dtos.request.AssociateCategoriesToProductRequest;
import tech.joelf.ms_relations.services.ProductCategoryService;

@Component
public class ProductSubscriber {

    private final ProductCategoryService productCategoryService;

    public ProductSubscriber(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @RabbitListener(queues = "${amqp.exchange.relations.products.name}")
    public void subscribe(@Payload String message) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            var event = mapper.readValue(message, AssociateCategoriesToProductRequest.class);

            productCategoryService.associateCategoriesToProduct(event);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
