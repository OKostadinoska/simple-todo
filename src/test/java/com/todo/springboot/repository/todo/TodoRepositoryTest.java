package com.todo.springboot.repository.todo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;

import com.todo.springboot.model.Todo;

import com.todo.springboot.repository.TodoRepository;

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class TodoRepositoryTest {

	@Autowired
	private TodoRepository repo;

	@Test
	public void testCreateTodo() {
		Todo newTodo = new Todo();

		newTodo.setTitle("Homework");
		newTodo.setDescription("Study Spanish");
		newTodo.setIsDone(false);
		newTodo.setTargetDate(new Date());

		Todo savedTodo = repo.save(newTodo);
		assertThat(savedTodo.getId()).isGreaterThan(0);
	}

	@Test
	public void testListAllTodos() {
		Iterable<Todo> listTodos = repo.findAll();

		listTodos.forEach(todo -> System.out.println(todo));
	}

	@Test
	public void testGetTodoById() {
		Integer todoId = 2;
		Todo todo = repo.findById(2).get();
		System.out.println(todo);

		assertThat(todo).isNotNull();
	}

	@Test
	public void testUpdateTodoDetails() {
		Todo Todo = repo.findById(2).get();
		Todo.setDescription("learn spring booooot");

		repo.save(Todo);

	}

	@Test
	public void testDeleteTodo() {
		Integer todoId = 2;
		repo.deleteById(todoId);
	}

	@Test
	public void testUndoneTodo() {
		Integer id = 1;
		repo.updateTodoStatus(id, false);

	}

	@Test
	public void testDoneTodo() {
		Integer id = 1;
		repo.updateTodoStatus(id, true);

	}
	
	@Test
	public void testListFirstPage() {
		int pageNumber = 4;
		int pageSize = 2;
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Todo> page = repo.findAll(pageable);
		
		List<Todo> listTodos = page.getContent();
		
		listTodos.forEach(todo -> System.out.println(todo));
		
		assertThat(listTodos.size()).isEqualTo(pageSize);
	}

}
