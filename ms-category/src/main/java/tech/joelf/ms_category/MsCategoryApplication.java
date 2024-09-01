package tech.joelf.ms_category;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MsCategoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsCategoryApplication.class, args);
	}
}
