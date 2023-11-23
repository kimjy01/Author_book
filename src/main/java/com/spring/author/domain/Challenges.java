package com.spring.author.domain;

import java.util.Set;

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
public class Challenges {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
    private Long id;

	@Column(name = "is_participation")
    private boolean is_participation;
	
	@Column(name = "is_success")
    private boolean is_success;

	@OneToMany(mappedBy = "challenge")
    private Set<ChallengeUsers> challengeUsers;

    @ManyToOne
    @JoinColumn(name = "author_book_id")
    private AuthorBooks author_book;

}
