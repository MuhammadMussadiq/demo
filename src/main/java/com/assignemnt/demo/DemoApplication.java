package com.assignemnt.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(value = "com.assignemnt.demo.repository")
@EnableMongoAuditing
public class DemoApplication {

	public static void main(String[] args) {
		System.out.println("Test");
		SpringApplication.run(DemoApplication.class, args);
	}

}
