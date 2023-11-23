package com.spring.author.domain;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChallengeUsers {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "challenge_id")
    private Challenges challenge;

    // 수정: ManyToOne 관계로 변경
    @ManyToOne
    @JoinColumn(name = "parent_challenge_user_id")
    private ChallengeUsers parentChallengeUser;

    // 추가: 자식 ChallengeUsers 리스트
    @OneToMany(mappedBy = "parentChallengeUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChallengeUsers> childChallengeUsers;
    
}
