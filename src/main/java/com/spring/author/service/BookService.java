package com.spring.author.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.author.domain.Users;
import com.spring.author.domain.BookReviews;
import com.spring.author.dto.AddBookRequest;
import com.spring.author.repository.BookReviewRepository;
import com.spring.author.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BookService {
	
	@Autowired
	private final BookReviewRepository bookRepository;
	
	@Autowired
	private final UserRepository userRepository;
	
	public Long save(AddBookRequest dto) {
		
		Users user = userRepository.findByEmail(dto.getUserId())
				.orElseThrow(() -> new EntityNotFoundException("사용자를 찾을 수 없습니다."));
		
		BookReviews savedBook = bookRepository.save(BookReviews.builder()
	            .book_name(dto.getBook_name())
	            .author_name(dto.getAuthor_name())
	            .thumbnail(dto.getThumbnail())
	            .isbn(dto.getIsbn())
	            .review_content(dto.getReview_content())
	            .user(user)
	            .build());

	    // 사용자의 rate 업데이트
	    updateRateByUserId(user.getId());

	    return savedBook.getId();
	}
	
	public List<BookReviews> bookList() {
		
		return bookRepository.findAll();
	}
	
	public List<BookReviews> getBookReviewsByUserId(Long userId) {
        return bookRepository.findByUser_Id(userId);
    }
	
	public List<BookReviews> bookDetail(String isbn){
		
		return bookRepository.findAllByIsbn(isbn);
	}
	
	public void bookDelete(Long id) {
			
		bookRepository.deleteById(id);
	}
	
	public BookReviews bookView(Long id) {
		
		return bookRepository.findById(id).get();
	}
	
	public void updateBook(Long bookId, AddBookRequest dto) {
	    BookReviews book = bookRepository.findById(bookId)
	            .orElseThrow(() -> new EntityNotFoundException("책을 찾을 수 없습니다."));

	    Users user = userRepository.findByEmail(dto.getUserId())
	            .orElseThrow(() -> new EntityNotFoundException("사용자를 찾을 수 없습니다."));

	    // Update the existing book information
	    book.setBook_name(dto.getBook_name());
	    book.setAuthor_name(dto.getAuthor_name());
	    book.setThumbnail(dto.getThumbnail());
	    book.setIsbn(dto.getIsbn());
	    book.setReview_content(dto.getReview_content());
	    book.setUser(user);

	    bookRepository.save(book);
	}
	
	public void updateRateByUserId(Long userId) {
	    // 사용자의 책 리뷰 목록을 가져옴
	    List<BookReviews> bookReviews = bookRepository.findByUser_Id(userId);

	    // 리뷰 개수에 따라 rate 계산
	    int reviewCount = bookReviews.size();
	    int newRate = (reviewCount / 10) + 1; // 10개마다 1씩 증가

	    // 현재 저장된 사용자 정보를 가져옴
	    Users user = userRepository.findById(userId)
	            .orElseThrow(() -> new EntityNotFoundException("사용자를 찾을 수 없습니다."));

	    // 현재 rate와 새로 계산한 rate가 다를 경우에만 업데이트
	    if (user.getRate() != newRate) {
	        user.setRate(newRate);
	        userRepository.save(user);
	    }
	}

}
