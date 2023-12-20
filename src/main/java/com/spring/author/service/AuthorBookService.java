package com.spring.author.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.author.domain.AuthorBooks;
import com.spring.author.domain.Authors;
import com.spring.author.dto.AddAuthorBookRequest;
import com.spring.author.repository.AuthorBookRepository;
import com.spring.author.repository.AuthorRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthorBookService {
	
	@Autowired
	private final AuthorBookRepository authorBookRepository;
	
	@Autowired
	private final AuthorRepository authorRepository;
	
	public Long save(AddAuthorBookRequest dto) {
		
		Authors author = authorRepository.findById(dto.getAuthorId())
				.orElseThrow(() -> new EntityNotFoundException("사용자를 찾을 수 없습니다."));
		
		AuthorBooks savedBook = authorBookRepository.save(AuthorBooks.builder()
	            .book_title(dto.getBook_title())
	            .book_image(dto.getBook_image())
	            .authors(author)
	            .build());

	    return savedBook.getId();
	}
	
	public List<AuthorBooks> authorBookList(Long id) {
		
		return authorBookRepository.findByAuthorsId(id);
	}

}
