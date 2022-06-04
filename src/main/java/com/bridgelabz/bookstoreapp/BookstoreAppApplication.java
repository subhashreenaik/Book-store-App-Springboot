package com.bridgelabz.bookstoreapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class BookstoreAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreAppApplication.class, args);
		System.out.println("welcome to Bookstore");
	}

}
