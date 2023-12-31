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
public class AuthorBooks {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
    private Long id;
	
	@Column(name = "book_title")
	private String book_title;
	
	@Column(name = "book_image")
    private String book_image;

	@ManyToOne
    @JoinColumn(name = "authorsId")
    private Authors authors;
}
