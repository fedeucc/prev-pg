package com.edilre.preventivatore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = { "com.edilre.preventivatore.repo" })
public class PreventivatoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(PreventivatoreApplication.class, args);
	}

}
