package com.clicktocart.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ClickToCartApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClickToCartApplication.class, args);
	}

}
