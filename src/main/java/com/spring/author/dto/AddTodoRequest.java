package com.spring.author.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddTodoRequest {
	
	@NotEmpty(message = "할 일을 입력해주세요.")
	private String todo_content;
	
	private LocalDate today_date;
	
	private String userId;
	
}
