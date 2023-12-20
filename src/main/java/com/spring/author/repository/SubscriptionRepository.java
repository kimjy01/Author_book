package com.spring.author.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.author.domain.Authors;
import com.spring.author.domain.Subscriptions;
import com.spring.author.domain.Users;

public interface SubscriptionRepository extends JpaRepository<Subscriptions, Long> {
	List<Subscriptions> findByUser(Users user);
	
	boolean existsByUserAndAuthor(Users user, Authors author);

    Subscriptions findByUserAndAuthor(Users user, Authors author);
}
