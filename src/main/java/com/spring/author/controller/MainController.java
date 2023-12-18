package com.spring.author.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.spring.author.domain.BookReviews;
import com.spring.author.domain.ChallengeUsers;
import com.spring.author.domain.PrincipalDetails;
import com.spring.author.domain.Users;
import com.spring.author.repository.UserRepository;
import com.spring.author.service.BookService;
import com.spring.author.service.UserDetailService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MainController {
	
	@Autowired
	private final UserDetailService userService;
	
	@Autowired
	private final BookService bookService;

	@GetMapping("/")
	public String main() {
		return "base";
	}
	
	@GetMapping("/nav")
	public String nav() {
		return "nav";
	}
	
	@GetMapping("/home")
	public String home(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {
		
		Users user = principalDetails.getUsers();
		
		model.addAttribute("user", user);
		
		return "home";
	}
	
	@GetMapping("/chatList")
	public String chatList(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {
		return "chat_list";
	}
	
	@GetMapping("/mypage")
	public String mypage(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {
		
		Users user = principalDetails.getUsers();
		
		Long bookReviewCnt = userService.getBookCnt(user.getEmail());
		Long challengeCnt = userService.getChallengeCnt(user.getEmail());
		
		model.addAttribute("bookCnt", bookReviewCnt);
		model.addAttribute("challengeCnt", challengeCnt);
		model.addAttribute("user", user);
		
		return "mypage";
	}
}
