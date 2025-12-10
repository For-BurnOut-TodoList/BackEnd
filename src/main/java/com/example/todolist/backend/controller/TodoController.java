package com.example.todolist.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.todolist.backend.dto.TodoCreateRequestDto;
import com.example.todolist.backend.dto.TodoResponseDto;
import com.example.todolist.backend.dto.TodoUpdateRequestDto;
import com.example.todolist.backend.service.TodoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<TodoResponseDto> createTodo(@RequestBody TodoCreateRequestDto dto) {
        try {
            TodoResponseDto response = todoService.createTodo(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<TodoResponseDto>> getTodos(@RequestParam("userId") String userIdMail) {
        try {
            List<TodoResponseDto> todos = todoService.getTodosByUser(userIdMail);
            return ResponseEntity.ok(todos);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoResponseDto> updateTodo(@PathVariable Long id, @RequestBody TodoUpdateRequestDto dto) {
        try {
            dto.setId(id);
            TodoResponseDto updated = todoService.updateTodo(dto);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        try {
            todoService.deleteTodo(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
