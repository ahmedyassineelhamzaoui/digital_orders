package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.app", "firebase"})
public class RentalHive1Application {


	public static void main(String[] args) {
		SpringApplication.run(RentalHive1Application.class, args);
	}

}
