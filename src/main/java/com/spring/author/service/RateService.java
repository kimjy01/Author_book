package com.spring.author.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.author.domain.Users;
import com.spring.author.domain.BookReviews;
import com.spring.author.domain.ChallengeUsers;
import com.spring.author.domain.Rate;
import com.spring.author.dto.AddBookRequest;
import com.spring.author.dto.AddUserRequest;
import com.spring.author.repository.AuthorBookRepository;
import com.spring.author.repository.BookReviewRepository;
import com.spring.author.repository.RateRepository;
import com.spring.author.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RateService {
	
	@Autowired
	private final RateRepository rateRepository;
	
	public Optional<Rate> findByRate(int rate) {
		
		return rateRepository.findByRate(rate);
	}
	
}