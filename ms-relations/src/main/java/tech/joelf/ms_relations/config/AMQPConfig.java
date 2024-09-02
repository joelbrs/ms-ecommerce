package tech.joelf.ms_relations.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AMQPConfig {

    @Value("${amqp.exchange.relations.product-category.name}")
    private String queueProductCategory;

    @Bean
    private Queue queueProductCategory() {
        return new Queue(queueProductCategory, Boolean.TRUE);
    }

    @Bean
    private RabbitTemplate productCategoryRabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setRoutingKey(queueProductCategory);
        return rabbitTemplate;
    }
}
