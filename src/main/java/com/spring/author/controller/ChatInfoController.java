package com.spring.author.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.author.domain.PrincipalDetails;
import com.spring.author.domain.Users;
import com.spring.author.dto.AddInfoRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
@RestController
public class ChatInfoController {
	
	@Autowired
	private UserDetailService userService;
	
	@Autowired
	private final AuthorService authorService;
	
	@Autowired
	private final AuthorBookService authorBookService;
	
	@RequestMapping("/author/profile")
	@ResponseBody
    public Map<Integer, String> getAuthorInfo(@RequestParam int id, Model model) {
        int author_id = id;
        Long authorId = Long.valueOf(author_id);

        List<AuthorBooks> authorBooks = authorBookService.authorBookList(authorId);
        
        Map<Integer, String> map = new HashMap<>();
        
        int i = 0;
        for (AuthorBooks authorBook : authorBooks) {

        	String bookImage = authorBook.getBook_image();
        	
        	map.put(i, bookImage);
        	i++;
        }
        
        return map;
    }
	
}
