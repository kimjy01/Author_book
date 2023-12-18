package com.spring.author.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Todo {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
    private Long id;

	@Column(name = "todo_content")
    private String todo_content;
	
	@Column(name = "today_date")
	@CreatedDate
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate today_date;
	
	@Column(name = "is_success")
	private boolean is_success;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private Users user;
	
	public Todo toggleIsSuccess() {
        return Todo.builder()
                .id(this.id)
                .todo_content(this.todo_content)
                .today_date(this.today_date)
                .is_success(!this.is_success)
                .user(this.user)
                .build();
    }
}
