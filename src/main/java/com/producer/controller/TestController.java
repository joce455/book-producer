package com.producer.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.producer.model.Book;

@RestController
public class TestController {
	
	@RequestMapping(value = "/books", method = RequestMethod.GET)
	@HystrixCommand(fallbackMethod = "getDataFallBack")
	public Book firstPage() {
		System.out.println("Inside firstPage");
		
		Book book = new Book();
		book.setDescription("The third best book");
		book.setIsbn("book1");
		book.setName("The witcher 3");
		book.setPrice(2.3);
		
		if(book.getName().equalsIgnoreCase("emp1"))
			throw new RuntimeException();
		
		return book;
	}
	
	public Book getDataFallBack() {
		
		System.out.println("Inside fallback");
		Book book = new Book();
		book.setDescription("fallback-desc");
		book.setIsbn("fallback-isbn");
		book.setName("fallback-name");
		book.setPrice(0);

		return book;
		
	}
}
