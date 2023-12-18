package com.spring.author.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddBookRequest {
	
	@NotEmpty(message = "책 제목을 입력해주세요.")
	private String book_name;
	
	@NotEmpty(message = "작가명을 입력해주세요.")
	private String author_name;
	
	@NotEmpty(message = "리뷰를 입력해주세요.")
	private String review_content;
	
	@NotEmpty
	private String thumbnail;
	
	@NotEmpty
	private String isbn;
	
	private String userId;
	
}
