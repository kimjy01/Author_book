package com.spring.author.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.author.domain.BookReviews;

public interface BookReviewRepository extends JpaRepository<BookReviews, Long> {

	List<BookReviews> findAllByIsbn(String isbn);
	
	List<BookReviews> findByUser_Id(Long id);
}
