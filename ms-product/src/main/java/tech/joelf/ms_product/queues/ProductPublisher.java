package tech.joelf.ms_product.queues;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import tech.joelf.ms_product.dtos.request.CreateProductRequest;
import tech.joelf.ms_product.utils.ConvertDataToJSON;

@Component
public class ProductPublisher {
    private final RabbitTemplate productsRabbitTemplate;
    private final Queue queueProducts;

    public ProductPublisher(RabbitTemplate productsRabbitTemplate, Queue queueProducts) {
        this.productsRabbitTemplate = productsRabbitTemplate;
        this.queueProducts = queueProducts;
    }

    public void publish(CreateProductRequest createProductRequest) {
        productsRabbitTemplate.convertAndSend(queueProducts.getName(), ConvertDataToJSON.convert(createProductRequest));
    }
}
