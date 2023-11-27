package com.spring.author.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.author.domain.Authors;

public interface AuthorRepository extends JpaRepository<Authors, Long> {

}
