package com.example.todolist.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todolist.backend.domain.Praise;

public interface PraiseRepository extends JpaRepository<Praise, Long> {
    List<Praise> findByTodoId(Long todoId);

    void deleteByTodoId(Long todoId);
}
