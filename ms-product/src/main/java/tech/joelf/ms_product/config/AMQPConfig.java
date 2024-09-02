package tech.joelf.ms_product.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class AMQPConfig {

    @Value("${amqp.exchange.relations.product-category.name}")
    private String queueProductCategory;

    @Bean
    public Queue queueProductCategory() {
        return new Queue(queueProductCategory, Boolean.TRUE);
    }

    @Bean
    public RabbitTemplate productCategoryRabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setRoutingKey(queueProductCategory);
        return rabbitTemplate;
    }
}
