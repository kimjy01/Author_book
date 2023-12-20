package com.spring.author.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spring.author.domain.Authors;
import com.spring.author.domain.Users;

public interface AuthorRepository extends JpaRepository<Authors, Long> {

	Optional<Authors> findByAuthorEmail(String email);
	
	List<Authors> findByIdNotIn(List<Long> authorIds);

}
