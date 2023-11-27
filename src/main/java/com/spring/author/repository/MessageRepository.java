package com.spring.author.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.author.domain.Messages;

public interface MessageRepository extends JpaRepository<Messages, Long> {

}
