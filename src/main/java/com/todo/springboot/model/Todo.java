package com.todo.springboot.model;

import java.util.Date;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "todos")
public class Todo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "title", length = 35, nullable = false)
	private String title;

	@Column(name = "description", length = 45, nullable = false)
	private String description;

	@DateTimeFormat(pattern = "dd/MM/yyyy h:mm a")
	private Date targetDate;

	@Column(name = "createdAt")
	private Boolean isDone;

	public Todo() {
	}

	public Todo(String title, String description, Date targetDate) {
		this.title = title;
		this.description = description;
		this.targetDate = targetDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}

	public Boolean getIsDone() {
		return isDone;
	}

	public void setIsDone(Boolean isDone) {
		this.isDone = isDone;
	}

	@Override
	public String toString() {
		return "Todo [id=" + id + ", title=" + title + ", description=" + description + ", targetDate=" + targetDate
				+ ", isDone=" + isDone + "]";
	}

}