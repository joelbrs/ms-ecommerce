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

    @Value("${amqp.exchange.relations.products.name}")
    private String queueProducts;

    @Bean
    public Queue queueProductCategory() {
        return new Queue(queueProductCategory, Boolean.TRUE);
    }

    @Bean
    public Queue queueProducts() {
        return new Queue(queueProducts, Boolean.TRUE);
    }

    @Bean
    public RabbitTemplate productCategoryRabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setRoutingKey(queueProductCategory);
        return rabbitTemplate;
    }

    @Bean
    public RabbitTemplate productsRabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setRoutingKey(queueProducts);
        return rabbitTemplate;
    }
}
