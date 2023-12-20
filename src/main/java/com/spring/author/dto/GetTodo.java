package com.spring.author.dto;

import java.util.List;

import com.spring.author.domain.Todo;
import com.spring.author.domain.Users;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetTodo {
	
	private List<Todo> todos;
    private Users user;
	
}
