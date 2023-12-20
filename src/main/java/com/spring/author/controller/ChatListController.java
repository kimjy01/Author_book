package com.spring.author.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.author.domain.PrincipalDetails;
import com.spring.author.domain.Users;
import com.spring.author.dto.AddInfoRequest;
import com.spring.author.repository.SubscriptionRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.author.domain.AuthorBooks;
import com.spring.author.domain.Authors;
import com.spring.author.domain.ChatRooms;
import com.spring.author.service.AuthorBookService;
import com.spring.author.service.AuthorService;
import com.spring.author.service.ChatRoomService;
import com.spring.author.service.SubscriptionService;
import com.spring.author.service.UserDetailService;
import com.spring.author.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ChatListController {
	
	@Autowired
	private UserDetailService userService;
	
	@Autowired
	private final AuthorService authorService;
	
	@Autowired
	private final AuthorBookService authorBookService;
	
	@Autowired
	private final SubscriptionService subscriptionService;
	
	@Autowired
	private final ChatRoomService chatRoomService;
	
	@GetMapping("/chatList")
	public String chatList(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {
		
		Users user = principalDetails.getUsers();
		List<Authors> authors = authorService.authorsList();
		
		model.addAttribute("user", user);
		model.addAttribute("authors", authors);
		
		// 사용자가 구독하지 않은 작가들을 가져옴
        List<Authors> notSubscribedAuthors = authorService.getAuthorsNotSubscribedByUser(user);
        model.addAttribute("notSubscribedAuthors", notSubscribedAuthors);
        
        List<Authors> subscribedAuthors = authorService.getSubscribedAuthorsByUser(user);
        model.addAttribute("subscribedAuthors", subscribedAuthors);
        
        model.addAttribute("chatRoomList", chatRoomService.getChatRooms());
		
		return "chat_list";
	}
	
	@PostMapping("/subscribe")
    public String subscribeToAuthor(@RequestParam Long userId, @RequestParam Long authorId) {
        subscriptionService.subscribeToAuthor(userId, authorId);
        return "redirect:/";
    }

    @PostMapping("/unsubscribe")
    public String unsubscribeFromAuthor(@RequestParam Long userId, @RequestParam Long authorId) {
        subscriptionService.unsubscribeFromAuthor(userId, authorId);
        return "redirect:/";
    }
	
}
