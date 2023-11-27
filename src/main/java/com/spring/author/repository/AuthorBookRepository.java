package com.spring.author.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.author.domain.AuthorBooks;

public interface AuthorBookRepository extends JpaRepository<AuthorBooks, Long> {

}
