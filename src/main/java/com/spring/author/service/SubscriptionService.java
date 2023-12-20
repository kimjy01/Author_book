package com.spring.author.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.author.domain.Users;
import com.spring.author.domain.Authors;
import com.spring.author.domain.Subscriptions;
import com.spring.author.repository.AuthorRepository;
import com.spring.author.repository.SubscriptionRepository;
import com.spring.author.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SubscriptionService {
	
	@Autowired
	private final AuthorRepository authorRepository;
	
	@Autowired
    private SubscriptionRepository subscriptionRepository;
	
	@Autowired
	private final UserRepository userRepository;
	
	public void subscribeToAuthor(Long userId, Long authorId) {
        Users user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        Authors author = authorRepository.findById(authorId).orElseThrow(() -> new EntityNotFoundException("Author not found"));

        // 이미 구독 중인지 확인
        if (!isSubscribed(user, author)) {
            Subscriptions subscription = new Subscriptions();
            subscription.setUser(user);
            subscription.setAuthor(author);
            subscriptionRepository.save(subscription);
        }
    }

    public void unsubscribeFromAuthor(Long userId, Long authorId) {
        Users user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        Authors author = authorRepository.findById(authorId).orElseThrow(() -> new EntityNotFoundException("Author not found"));

        // 이미 구독 중인 경우에만 해지
        if (isSubscribed(user, author)) {
            Subscriptions subscription = subscriptionRepository.findByUserAndAuthor(user, author);
            subscriptionRepository.delete(subscription);
        }
    }

    public boolean isSubscribed(Users user, Authors author) {
        return subscriptionRepository.existsByUserAndAuthor(user, author);
    }
	
}