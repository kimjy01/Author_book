package com.spring.author.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.author.domain.Users;
import com.spring.author.domain.AuthorBooks;
import com.spring.author.domain.Authors;
import com.spring.author.domain.BookReviews;
import com.spring.author.domain.ChallengeUsers;
import com.spring.author.domain.Rate;
import com.spring.author.domain.Subscriptions;
import com.spring.author.dto.AddBookRequest;
import com.spring.author.dto.AddInfoRequest;
import com.spring.author.dto.AddUserRequest;
import com.spring.author.repository.AuthorBookRepository;
import com.spring.author.repository.AuthorRepository;
import com.spring.author.repository.BookReviewRepository;
import com.spring.author.repository.RateRepository;
import com.spring.author.repository.SubscriptionRepository;
import com.spring.author.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthorService {
	
	@Autowired
	private final AuthorRepository authorRepository;
	
	@Autowired
    private SubscriptionRepository subscriptionRepository;
	
	@Autowired
	private final AuthorBookRepository authorBookRepository;
	
	public void createAuthorsEntity(String authorName, String email) {
        Authors author = Authors.builder()
                .author_name(authorName)
                .authorEmail(email)
                .build();

        // Authors 엔티티를 저장
        authorRepository.save(author);
    }
	
	public Authors authorView(String email) {
		
		return authorRepository.findByAuthorEmail(email).get();
	}
	
	public List<Authors> authorsList(){
		
		return authorRepository.findAll();
	}
	
	public void updateInfo(Long id, AddInfoRequest dto) {
	    Authors author = authorRepository.findById(id)
	            .orElseThrow(() -> new EntityNotFoundException("작가를 찾을 수 없습니다."));

	    // Update the existing book information
	    author.setAuthor_info(dto.getAuthor_info());

	    authorRepository.save(author);
	}
	
	public List<Authors> getAuthorsNotSubscribedByUser(Users user) {
        // 1. 특정 사용자의 구독 목록을 조회
        List<Subscriptions> subscriptions = subscriptionRepository.findByUser(user);

        // 2. 구독 목록이 없으면 모든 작가를 가져옴
        if (subscriptions.isEmpty()) {
            return authorRepository.findAll();
        }

        // 3. 특정 사용자가 구독한 작가를 제외한 나머지 작가들을 가져옴
        List<Long> subscribedAuthorIds = subscriptions.stream()
                .map(subscription -> subscription.getAuthor().getId())
                .collect(Collectors.toList());

        return authorRepository.findByIdNotIn(subscribedAuthorIds);
    }
	
	public List<Authors> getSubscribedAuthorsByUser(Users user) {
	    // 1. 특정 사용자의 구독 목록을 조회
	    List<Subscriptions> subscriptions = subscriptionRepository.findByUser(user);

	    // 2. 구독 목록이 없으면 빈 리스트 반환
	    if (subscriptions.isEmpty()) {
	        return Collections.emptyList();
	    }

	    // 3. 특정 사용자가 구독한 작가들을 가져옴
	    List<Long> subscribedAuthorIds = subscriptions.stream()
	            .map(subscription -> subscription.getAuthor().getId())
	            .collect(Collectors.toList());

	    return authorRepository.findAllById(subscribedAuthorIds);
	}

	public Authors findById(Long authorId) {
		// TODO Auto-generated method stub
		return authorRepository.findById(authorId).get();
	}
	
}