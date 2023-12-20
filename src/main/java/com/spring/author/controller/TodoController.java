package com.spring.author.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.author.domain.BookReviews;
import com.spring.author.domain.PrincipalDetails;
import com.spring.author.domain.Todo;
import com.spring.author.domain.Users;
import com.spring.author.dto.AddBookRequest;
import com.spring.author.dto.AddTodoRequest;
import com.spring.author.service.BookService;
import com.spring.author.service.TodoService;

import jakarta.validation.Valid;

@Controller
public class TodoController {

	@Autowired 
	private TodoService todoService;
	
	@GetMapping("/challenge")
	public String challenge(@RequestParam(required = false) String date,
	                        @AuthenticationPrincipal PrincipalDetails principalDetails,
	                        Model model) {
	    
	    Users user = principalDetails.getUsers();
	    model.addAttribute("user", user);

	    LocalDate selectedDate;
	    if (date != null && !date.isEmpty()) {
	        selectedDate = LocalDate.parse(date);
	    } else {
	        // 날짜 파라미터가 주어지지 않은 경우 현재 날짜를 기본값으로 사용
	        selectedDate = LocalDate.now();
	    }

	    System.out.println(selectedDate);

	    List<Todo> todos = todoService.getTodoByUserIdAndDateAfter(user.getId(), selectedDate);
	    model.addAttribute("list", todos);

	    return "challenge";
	}
	
	@PostMapping("/todo/add")
	public String addTodo(@Valid AddTodoRequest request) {
		todoService.save(request);
		
		return "redirect:/";
	}
	
	@PostMapping("/todo/{id}")
	@ResponseBody
    public void toggleCheck(@RequestParam Integer id) {
		
		Long todoId = Long.valueOf(id);
		
        todoService.toggleCheck(todoId);
    }
	
	@GetMapping("/todo/delete")
	public String todoDelete(Long id) {
		
		todoService.todoDelete(id);
		
		return "redirect:/";
	}
}
