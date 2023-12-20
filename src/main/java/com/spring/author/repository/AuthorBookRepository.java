package com.spring.author.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.author.domain.AuthorBooks;
import com.spring.author.domain.Authors;

public interface AuthorBookRepository extends JpaRepository<AuthorBooks, Long> {

	List<AuthorBooks> findByAuthorsId(Long id);
	
	List<AuthorBooks> findByAuthors(Authors authors);
}
