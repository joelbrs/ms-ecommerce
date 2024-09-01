package tech.joelf.ms_product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MsProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsProductApplication.class, args);
	}
}
