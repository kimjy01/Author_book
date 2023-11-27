package com.spring.author.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.author.domain.Subscriptions;

public interface SubscriptionRepository extends JpaRepository<Subscriptions, Long> {

}
