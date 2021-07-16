package com.todo.springboot.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;


import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.todo.springboot.model.Todo;

@Repository("TodoRepository")
public interface TodoRepository extends PagingAndSortingRepository<Todo, Integer> {

	@Query("UPDATE Todo t SET t.isDone = ?2 WHERE t.id = ?1")
	@Modifying
	public void updateTodoStatus(Integer id, boolean isDone);
	
	public Long countById(Integer id);

	List<Todo> findAll(Sort sort);

}
