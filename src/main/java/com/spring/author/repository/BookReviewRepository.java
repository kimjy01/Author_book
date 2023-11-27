package com.spring.author.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.author.domain.BookReviews;

public interface BookReviewRepository extends JpaRepository<BookReviews, Long> {

}
