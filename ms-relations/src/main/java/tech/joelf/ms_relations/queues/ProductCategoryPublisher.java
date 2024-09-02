package tech.joelf.ms_relations.queues;

import java.util.List;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import tech.joelf.ms_relations.model.ProductCategory;
import tech.joelf.ms_relations.utils.ConvertDataToJSON;

@Component
public class ProductCategoryPublisher {
    private final RabbitTemplate productCategoryRabbitTemplate;
    private final Queue queueProductCategory;

    public ProductCategoryPublisher(RabbitTemplate productCategoryRabbitTemplate, Queue queueProductCategory) {
        this.productCategoryRabbitTemplate = productCategoryRabbitTemplate;
        this.queueProductCategory = queueProductCategory;
    }

    public void sendCreateRelation(List<ProductCategory> productCategories) {
        productCategoryRabbitTemplate.convertAndSend(queueProductCategory.getName(),
                ConvertDataToJSON.convert(productCategories));
    }
}
