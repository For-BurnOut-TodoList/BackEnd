package com.example.todolist.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todolist.backend.domain.Todo;
import com.example.todolist.backend.domain.User;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByUser(User user);
}
