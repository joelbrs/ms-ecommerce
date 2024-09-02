package tech.joelf.ms_relations.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AMQPConfig {

    @Value("${amqp.exchange.relations.name}")
    private String queueRelationsName;

    @Bean
    private Queue queueRelations() {
        return new Queue(queueRelationsName, Boolean.TRUE);
    }
}
