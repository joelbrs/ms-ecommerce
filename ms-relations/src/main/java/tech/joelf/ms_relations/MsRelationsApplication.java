package tech.joelf.ms_relations;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableRabbit
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class MsRelationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsRelationsApplication.class, args);
	}
}
