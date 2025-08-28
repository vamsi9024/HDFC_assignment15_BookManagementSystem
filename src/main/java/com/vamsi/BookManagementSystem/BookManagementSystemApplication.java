package com.vamsi.BookManagementSystem;

import com.vamsi.BookManagementSystem.service.BookService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookManagementSystemApplication {

	public static void main(String[] args) {

		SpringApplication.run(BookManagementSystemApplication.class, args);

		BookService.MostExpensiveBook();
	}

}
