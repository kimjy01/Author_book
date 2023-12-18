package com.spring.author.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.author.domain.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {

	List<Todo> findByUser_Id(Long id);
}
