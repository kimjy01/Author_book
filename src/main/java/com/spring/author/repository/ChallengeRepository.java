package com.spring.author.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.author.domain.Challenges;

public interface ChallengeRepository extends JpaRepository<Challenges, Long> {

}
