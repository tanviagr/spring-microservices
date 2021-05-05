package com.example.demo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Basic Social Media App API",
				version = "v1.0.0",
				description = "This app provides REST APIs for social media users and their posts",
				contact = @Contact(
						name = "Tanvi Agrawal",
						email = "tanvia29@gmail.com",
						url = "http://abc.com"
				)
		)
)
public class RestfulWebServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfulWebServicesApplication.class, args);
	}

}
