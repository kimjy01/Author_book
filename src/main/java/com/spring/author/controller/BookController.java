package com.spring.author.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.author.domain.BookReviews;
import com.spring.author.domain.PrincipalDetails;
import com.spring.author.domain.Users;
import com.spring.author.dto.AddBookRequest;
import com.spring.author.service.BookService;

import jakarta.validation.Valid;

@Controller
public class BookController {

	@Autowired 
	private BookService bookService;
	
	@GetMapping("/book")
	public String book(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {
		
		Users user = principalDetails.getUsers();
		model.addAttribute("user", user);
		
		List<BookReviews> bookReviews = bookService.getBookReviewsByUserId(user.getId());
        model.addAttribute("bookReviews", bookReviews);
		
		model.addAttribute("list", bookService.bookList());
		
		return "book";
	}
	
	@PostMapping("/add")
	public String addBookReivew(@Valid AddBookRequest request) {
		bookService.save(request);
		
		return "redirect:/";
	}
	
	@GetMapping("/bookSearch")
	public String bookSearch() {
		
		return "book_search";
	}
	
	@PostMapping("/book/update")
	public String bookUpdate(@RequestParam Long bookId, @Valid AddBookRequest request) {
		
		bookService.updateBook(bookId, request);

		return "redirect:/";
	}
	
	@GetMapping("/book/delete")
	public String bookDelete(Long id) {
		
		bookService.bookDelete(id);
		
		return "redirect:/";
	}
	
}
