package com.spring.author.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.author.domain.PrincipalDetails;
import com.spring.author.service.AuthorService;
import com.spring.author.service.ChatRoomService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ChatController {
	
	@Autowired
	private final ChatRoomService chatRoomService;
	
	@Autowired
	private final AuthorService authorService;
	
	@RequestMapping("/chat/{chatId}")
	public String chat(@PathVariable Long chatId ,@AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {
		
		System.out.println(principalDetails.getUsers().getName());
		
		model.addAttribute("username", principalDetails.getUsers().getName());
		
		String chatRoomName = chatRoomService.getChatRoomById(chatId).get().getChatRoomName();
		
		model.addAttribute("roomName", authorService.authorView(chatRoomName).getAuthor_name());
		model.addAttribute("roomNumber", chatRoomService.getChatRoomById(chatId).get().getId());
		
		return "chat";
	}
	
}
