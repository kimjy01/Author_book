package com.spring.author.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.author.domain.ChatRooms;

public interface ChatRoomRepository extends JpaRepository<ChatRooms, Long> {

}
