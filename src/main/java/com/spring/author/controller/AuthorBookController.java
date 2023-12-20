package com.spring.author.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.author.dto.AddAuthorBookRequest;
import com.spring.author.service.AuthorBookService;
import com.spring.author.service.AuthorService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AuthorBookController {
	
	@Autowired
	private final AuthorService authorService;
	
	@Autowired
	private final AuthorBookService authorBookService;

	@PostMapping("/author/book/add")
	public String addAuthorBook(@Valid AddAuthorBookRequest request) {
		
		authorBookService.save(request);
		
		return "redirect:/";
	}
	
}
