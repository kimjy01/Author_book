package com.spring.author.service;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Autowired
	private final UserRepository userRepository;
	
	@Autowired
	private final AuthorService authorService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users users = userRepository.findByEmail(username)
				.orElseThrow(()-> {
					return new UsernameNotFoundException("해당 유저를 찾을 수 없습니다.");
				});
		
		return new PrincipalDetails(users);
	}
	
	// 사용자의 도서 리뷰 개수 조회
	public Long getBookCnt(String email) {
	    Users user = userRepository.findByEmail(email).orElse(null);
	    if (user != null) {	
	    	return userRepository.countBookReviewsByBookReviewsUser(user);
	    }
	    return 0L;
	}
	
	public void changeUserRoleToAuthor(String email) {
		
        Users user = userRepository.findByEmail(email).orElse(null);
        if (user != null) {
            user.setRole("AUTHOR");
            userRepository.save(user);
            
            authorService.createAuthorsEntity(user.getName(), user.getEmail());
        }
    }

}
