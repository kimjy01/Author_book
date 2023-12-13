package com.spring.author.dto;

import com.spring.author.domain.Users;

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
	
	@NotNull
	private Boolean is_public;
	
	private String userId;
	
}
