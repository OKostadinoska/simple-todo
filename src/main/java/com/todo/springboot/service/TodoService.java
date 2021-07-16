package com.todo.springboot.service;

import java.util.List;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.todo.springboot.repository.TodoRepository;
import com.todo.springboot.model.Todo;

@Service
@Transactional
public class TodoService {

	public static final int TODOS_PER_PAGE = 10;

	@Autowired
	private TodoRepository repo;

	public List<Todo> getAllTodos() {
		return (List<Todo>) repo.findAll();
	}

	public Page<Todo> listByPage(int PageNum, String sortField, String sortDir) {
		Sort sort = Sort.by(sortField);

		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
		Pageable pageable = PageRequest.of(PageNum - 1, TODOS_PER_PAGE, sort);
		return repo.findAll(pageable);
	}

	public void saveTodo(Todo todo) {
		this.repo.save(todo);
	}

	public Todo getTodoByID(Integer id) {
		Optional<Todo> optional = repo.findById(id);
		Todo todo = null;
		if (optional.isPresent()) {
			todo = optional.get();
		} else {
			throw new RuntimeException("Todo not found for id :: " + id);
		}
		return todo;
	}

	public void deleteTodoById(Integer id) {
		this.repo.deleteById(id);

	}

	public void updateTodostatus(Integer id, boolean isDone) {
		repo.updateTodoStatus(id, isDone);
	}

}
