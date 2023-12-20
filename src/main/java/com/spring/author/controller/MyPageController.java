package com.spring.author.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.author.domain.PrincipalDetails;
import com.spring.author.domain.Users;
import com.spring.author.dto.AddInfoRequest;
import com.spring.author.domain.AuthorBooks;
import com.spring.author.domain.Authors;
import com.spring.author.service.AuthorBookService;
import com.spring.author.service.AuthorService;
import com.spring.author.service.UserDetailService;
import com.spring.author.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MyPageController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private UserDetailService userService;
	
	@Autowired
	private final AuthorService authorService;
	
	@Autowired
	private final AuthorBookService authorBookService;

	@GetMapping("/mypage")
	public String mypage(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {
		
		Users user = principalDetails.getUsers();
		
		Long bookReviewCnt = userService.getBookCnt(user.getEmail());
		
		if (user.getRole().equals("AUTHOR")) {
			Authors author = authorService.authorView(user.getEmail());
			List<AuthorBooks> authorBooks = authorBookService.authorBookList(author.getId());

			model.addAttribute("author", author);
			model.addAttribute("bookList", authorBooks);
		}
		
		model.addAttribute("bookCnt", bookReviewCnt);
		model.addAttribute("user", user);
		
		return "mypage";
	}
	
	@RequestMapping("/changeRole")
	public String changeRole(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {
		
		Users user = principalDetails.getUsers();
		String email = user.getEmail();
		
		userService.changeUserRoleToAuthor(email);
		
		// 인증 정보를 다시 새로고침
	    Authentication authentication = authenticationManager.authenticate(
	            new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities()));
	    SecurityContextHolder.getContext().setAuthentication(authentication);

		return "redirect:/";
	}
	
	@GetMapping("/authorSearch")
	public String authorSearch() {
		
		return "author_search";
	}
	
	@PostMapping("/info/add")
	public String updateInfo(@RequestParam Long id, @Valid AddInfoRequest request) {
		
		authorService.updateInfo(id, request);
		
		return "redirect:/";
	}
}
