package com.spring.author.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddAuthorBookRequest {
	
	private String book_title;

	private String book_image;
	
	private Long authorId;
	
}
