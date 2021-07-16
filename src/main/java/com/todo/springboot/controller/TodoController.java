package com.todo.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.todo.springboot.model.Todo;
import com.todo.springboot.service.TodoService;

@Controller
@RequestMapping
public class TodoController {

	@Autowired
	private TodoService todoService;

	@GetMapping("/todos")
	public String listFirstPage(Model model) {
		return listByPage(1, model, "isDone", "dsc");
	}

	@GetMapping("/login")
	public String viewLoginPage() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			return "login";
		}

		return "redirect:/";
	}

	@GetMapping("/showNewTodoForm")
	public String showNewTodoForm(Model model) {
		Todo todo = new Todo();
		todo.setIsDone(false);
		model.addAttribute("todo", todo);
		return "new_todo";

	}

	@PostMapping("/saveTodo")
	public String saveTodo(@ModelAttribute("todo") Todo todo) {
		// save todo to the database
		todoService.saveTodo(todo);
		return "redirect:/todos";
	}

	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable(value = "id") Integer id, Model model) {
		// get todo from the service
		Todo todo = todoService.getTodoByID(id);

		// set todo as model attribute to pre-populate the form
		model.addAttribute("todo", todo);
		return "update_todo";

	}

	@GetMapping("/deleteTodo/{id}")
	public String deleteTodo(@PathVariable(value = "id") Integer id) {

		// call delete todo method

		this.todoService.deleteTodoById(id);
		return "redirect:/todos";

	}

	@GetMapping("/todos/{id}/isDone/{status}")
	public String updateTodoStatus(@PathVariable("id") Integer id, @PathVariable("status") boolean isDone) {
		todoService.updateTodostatus(id, isDone);

		return "redirect:/todos";

	}

	@GetMapping("/todos/page/{pageNum}")
	public String listByPage(@PathVariable(name = "pageNum") int pageNum, Model model,
			@Param("sortField") String sortField, @Param("sortDir") String sortDir) {

		System.out.println("Sort Field: " + sortField);
		System.out.println("Sort Order: " + sortDir);

		Page<Todo> page = todoService.listByPage(pageNum, sortField, sortDir);
		List<Todo> listTodos = page.getContent();

		long startCount = (pageNum - 1) * TodoService.TODOS_PER_PAGE + 1;
		long endCount = startCount + TodoService.TODOS_PER_PAGE - 1;
		if (endCount > page.getTotalElements()) {
			endCount = page.getTotalElements();
		}

		model.addAttribute("startCount", startCount);
		model.addAttribute("endCount", endCount);
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listTodos", listTodos);

		return "todos";
	}

}
