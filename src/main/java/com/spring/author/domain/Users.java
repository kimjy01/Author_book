package com.spring.author.domain;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;
    
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    
    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "rate")
    @ColumnDefault("1")
    private int rate;

    @Column(name = "role")
    @ColumnDefault("READER")
    private String role;

    @ManyToOne
    @JoinColumn(name = "author_book_id")
    private AuthorBooks authorBook;
    
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Authors author; 

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookReviews> bookReviews = new ArrayList<>();
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Todo> todos = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Subscriptions> subscriptions = new ArrayList<>();
    
}
