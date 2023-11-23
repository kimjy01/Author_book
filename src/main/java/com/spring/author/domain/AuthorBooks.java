package com.spring.author.domain;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorBooks {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
    private Long id;
	
	@Column(name = "book_title")
	private String book_title;
	
	@Column(name = "book_content")
    private String book_content;
	
	@Column(name = "book_type")
    private String book_type;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Authors author;
}
