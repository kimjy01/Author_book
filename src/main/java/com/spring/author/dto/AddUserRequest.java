package com.spring.author.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddUserRequest {
	
	@NotEmpty(message = "이름을 입력해주세요.")
	private String name;

	@Email
	@NotEmpty(message = "이메일을 입력해주세요.")
	private String email;
	
	@NotEmpty(message = "비밀번호를 입력해주세요.")
	private String password;
	
	@NotEmpty(message = "비밀번호 확인을 입력해주세요.")
	private String passwordCheck;
	
	private String role;
	
}
