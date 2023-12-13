package com.spring.author.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.author.dto.AddBookRequest;
import com.spring.author.service.BookService;

import jakarta.validation.Valid;

@Controller
public class BookController {

	@Autowired 
	private BookService bookService;
	
	@PostMapping("/add")
	public String addBookReivew(@Valid AddBookRequest request) {
		bookService.save(request);
		
		return "redirect:/";
	}
}
