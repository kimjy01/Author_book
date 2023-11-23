package com.spring.author.domain;

import java.util.ArrayList;
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
public class BookReviews {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
    private Long id;

	@Column(name = "book_name")
    private String book_name;
	
	@Column(name = "author_name")
    private String author_name;
	
	@Column(name = "review_content")
    private String review_content;
	
	@Column(name = "is_public")
    private boolean is_public;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private Users user;
}
