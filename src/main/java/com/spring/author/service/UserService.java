package com.spring.author.service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.author.domain.Users;
import com.spring.author.domain.ChallengeUsers;
import com.spring.author.dto.AddUserRequest;
import com.spring.author.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public Long save(AddUserRequest dto) {
		
		return userRepository.save(Users.builder()
				.name(dto.getName())
				.email(dto.getEmail())
				.password(bCryptPasswordEncoder.encode(dto.getPassword()))
				.role("READER")
				.build()).getId();
	}

}
