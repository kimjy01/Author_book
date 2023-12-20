package com.spring.author.service;

import java.util.List;
import java.util.Optional;

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
	
	public List<ChatRooms> getChatRooms() {
		
        return chatRoomRepository.findAll();
    }
	
	public Optional<ChatRooms> getChatRoomById(Long id) {
		return chatRoomRepository.findById(id);
	}
}
