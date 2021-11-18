package com.messenger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Using spring boot application to create backend API's for simple messenger
@SpringBootApplication
public class SimpleMessengerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleMessengerApplication.class, args);
	}

}
