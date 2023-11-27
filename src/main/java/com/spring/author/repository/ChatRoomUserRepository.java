package com.spring.author.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.author.domain.ChatRoomUsers;

public interface ChatRoomUserRepository extends JpaRepository<ChatRoomUsers, Long> {

}
