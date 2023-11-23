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
public class Authors {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
    private Long id;
	
	@Column(name = "author_name")
	private String author_name;
	
	@Column(name = "author_info")
    private String author_info;

	@OneToMany(mappedBy = "authors")
    private List<AuthorBooks> authorBooks;
}
