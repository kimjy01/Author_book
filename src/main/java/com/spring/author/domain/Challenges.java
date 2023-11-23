package com.spring.author.domain;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

    @OneToMany(mappedBy = "challenges")
    private List<ChallengeUsers> challenge_users;

    @ManyToOne
    @JoinColumn(name = "author_book_id")
    private AuthorBooks author_book;

}
