package com.spring.author.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.author.domain.Rate;
import com.spring.author.repository.RateRepository;

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