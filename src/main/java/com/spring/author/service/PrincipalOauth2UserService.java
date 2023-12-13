package com.spring.author.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.spring.author.domain.PrincipalDetails;
import com.spring.author.domain.Users;
import com.spring.author.oauth.GoogleUserInfo;
import com.spring.author.oauth.KakaoUserInfo;
import com.spring.author.oauth.OAuth2UserInfo;
import com.spring.author.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

	private final UserRepository userRepository;
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		
		OAuth2User oAuth2User = super.loadUser(userRequest);
		log.info("getAttirbutes : {}", oAuth2User.getAttributes());
		
		OAuth2UserInfo oAuth2UserInfo = null;
		
		String provider = userRequest.getClientRegistration().getRegistrationId();	
		
		if (provider.equals("google")) {
			
			log.info("구글 로그인 요청");
			oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
			
		} else if (provider.equals("kakao")) {
			
			log.info("카카오 로그인 요청");
			oAuth2UserInfo = new KakaoUserInfo( (Map)oAuth2User.getAttributes());
		}

		String email = oAuth2UserInfo.getEmail();
		String name = oAuth2UserInfo.getName();
		
		Optional<Users> optionalUsers = userRepository.findByEmail(email);
		Users users = null;
		
		if (optionalUsers.isEmpty()) {
			users = Users.builder()
					.email(email)
					.name(name)
					.role("READER")
					.build();
			userRepository.save(users);
		} else {
			users = optionalUsers.get();
		}
		
		
		return new PrincipalDetails(users, oAuth2User.getAttributes());
	}
}
