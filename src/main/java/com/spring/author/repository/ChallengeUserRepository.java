package com.spring.author.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.author.domain.ChallengeUsers;

public interface ChallengeUserRepository extends JpaRepository<ChallengeUsers, Long> {

}
