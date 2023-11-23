package com.spring.author.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.author.domain.PrincipalDetails;
import com.spring.author.domain.Users;
import com.spring.author.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserDetailService implements UserDetailsService {
	
	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users users = userRepository.findByEmail(username)
				.orElseThrow(()-> {
					return new UsernameNotFoundException("해당 유저를 찾을 수 없습니다.");
				});
		
		return new PrincipalDetails(users);
	}

}
