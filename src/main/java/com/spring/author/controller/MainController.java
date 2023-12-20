package com.spring.author.controller;

import java.net.URI;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.author.domain.BookReviews;
import com.spring.author.domain.ChallengeUsers;
import com.spring.author.domain.PrincipalDetails;
import com.spring.author.domain.Rate;
import com.spring.author.domain.Todo;
import com.spring.author.domain.Users;
import com.spring.author.repository.UserRepository;
import com.spring.author.service.BookApiService;
import com.spring.author.service.BookService;
import com.spring.author.service.RateService;
import com.spring.author.service.TodoService;
import com.spring.author.service.UserDetailService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MainController {
	
	@Autowired
	private final UserDetailService userService;
	
	@Autowired
	private final BookService bookService;
	
	@Autowired
	private final BookApiService bookApiService;
	
	@Autowired
	private final TodoService todoService;
	
	@Autowired
	private final RateService rateService;

	@GetMapping("/")
	public String main() {
		return "base";
	}
	
	@GetMapping("/nav")
	public String nav() {
		return "nav";
	}
	
	@GetMapping("/home")
	public String home(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model) throws JsonProcessingException {
		
		Users user = principalDetails.getUsers();
		model.addAttribute("user", user);
		
		List<BookReviews> bookReviews = bookService.getBookReviewsByUserId(user.getId());
        model.addAttribute("bookReviews", bookReviews);
        
        List<Map<String, Object>> recommandList = bookApiService.getBookApi();
        model.addAttribute("bookList", recommandList);
        
        LocalDate selectedDate = LocalDate.now();
        List<Todo> todos = todoService.getTodoByUserIdAndDateAfter(user.getId(), selectedDate);
	    model.addAttribute("list", todos);
	    
	    Long bookReviewCnt = userService.getBookCnt(user.getEmail());
	    model.addAttribute("bookCnt", bookReviewCnt);
	    
	    int rate = user.getRate();
	    
	    Optional<Rate> rate_info = rateService.findByRate(rate);
	    
	    model.addAttribute("rate", rateService.findByRate(rate).get());
        
		return "home";
	}
	
}
