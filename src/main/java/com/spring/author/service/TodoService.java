package com.spring.author.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.author.domain.Users;
import com.spring.author.domain.BookReviews;
import com.spring.author.domain.ChallengeUsers;
import com.spring.author.domain.Todo;
import com.spring.author.dto.AddBookRequest;
import com.spring.author.dto.AddTodoRequest;
import com.spring.author.dto.AddUserRequest;
import com.spring.author.repository.AuthorBookRepository;
import com.spring.author.repository.BookReviewRepository;
import com.spring.author.repository.TodoRepository;
import com.spring.author.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TodoService {
	
	@Autowired
	private final TodoRepository todoRepository;
	
	@Autowired
	private final UserRepository userRepository;
	
	public Long save(AddTodoRequest dto) {
		
		Users user = userRepository.findByEmail(dto.getUserId())
				.orElseThrow(() -> new EntityNotFoundException("사용자를 찾을 수 없습니다."));
		
		return todoRepository.save(Todo.builder()
				.todo_content(dto.getTodo_content())
				.todayDate(LocalDate.now())
				.user(user)
				.build()).getId();
	}
	
	public List<Todo> getTodoByUserIdAndDateAfter(Long userId, LocalDate today_date) {
	    return todoRepository.findByUser_IdAndTodayDate(userId, today_date);
	}
	
	public Todo toggleCheck(Long todoId) {
        Todo originalTodo = todoRepository.findById(todoId)
            .orElseThrow(() -> new IllegalArgumentException("Todo not found with id: " + todoId));

        // is_success 값을 토글한 새로운 Todo 생성
        originalTodo.set_success(!originalTodo.is_success());

        // 변경된 값을 저장
        return todoRepository.save(originalTodo);
    }
	
	public void todoDelete(Long id) {
		
		todoRepository.deleteById(id);
	}

}
