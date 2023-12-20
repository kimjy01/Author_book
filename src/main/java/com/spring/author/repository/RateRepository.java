package com.spring.author.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.author.domain.Rate;
import com.spring.author.domain.Users;

public interface RateRepository extends JpaRepository<Rate, Long> {

	Optional<Rate> findByRate(int rate);
}
