package com.spring.author.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.author.domain.ChatRooms;
import com.spring.author.repository.ChatRoomRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ChatRoomService {

	@Autowired
	private final ChatRoomRepository chatRoomRepository;
	
	public ChatRooms createChatRoom(String chatRoomName) {
		ChatRooms chatRoom = ChatRooms.builder()
				.chatRoomName(chatRoomName)
				.build();
		
		return chatRoomRepository.save(chatRoom);
	}
}
