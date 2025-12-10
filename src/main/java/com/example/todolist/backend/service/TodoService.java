package com.example.todolist.backend.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.todolist.backend.domain.Todo;
import com.example.todolist.backend.domain.User;
import com.example.todolist.backend.dto.TodoCreateRequestDto;
import com.example.todolist.backend.dto.TodoResponseDto;
import com.example.todolist.backend.dto.TodoUpdateRequestDto;
import com.example.todolist.backend.repository.PraiseRepository;
import com.example.todolist.backend.repository.TodoRepository;
import com.example.todolist.backend.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;
    private final PraiseRepository praiseRepository;

    public TodoResponseDto createTodo(TodoCreateRequestDto dto) {
        User user = userRepository.findByIdMail(dto.getUserIdMail())
                .orElseThrow(() -> new IllegalArgumentException("User not found."));

        Todo todo = Todo.builder()
                .user(user)
                .todo(dto.getTodo())
                .section(dto.getSection())
                .isDone(false)
                .createdAt(LocalDateTime.now())
                .build();

        Todo savedTodo = todoRepository.save(todo);
        return toDto(savedTodo);
    }

    public List<TodoResponseDto> getTodosByUser(String userIdMail) {
        User user = userRepository.findByIdMail(userIdMail)
                .orElseThrow(() -> new IllegalArgumentException("User not found."));

        return todoRepository.findByUser(user)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public TodoResponseDto updateTodo(TodoUpdateRequestDto dto) {
        Todo todo = todoRepository.findById(dto.getId())
                .orElseThrow(() -> new IllegalArgumentException("Todo not found."));

        todo.setTodo(dto.getTodo());
        todo.setSection(dto.getSection());

        boolean wasDone = Boolean.TRUE.equals(todo.getIsDone());
        todo.setIsDone(dto.getIsDone());

        if (Boolean.TRUE.equals(dto.getIsDone()) && !wasDone && todo.getCompletedAt() == null) {
            todo.setCompletedAt(LocalDateTime.now());
        }

        Todo updatedTodo = todoRepository.save(todo);
        return toDto(updatedTodo);
    }

    @Transactional
    public void deleteTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Todo not found."));

        praiseRepository.deleteByTodoId(todo.getId());
        todoRepository.delete(todo);
    }

    private TodoResponseDto toDto(Todo todo) {
        return TodoResponseDto.builder()
                .id(todo.getId())
                .todo(todo.getTodo())
                .section(todo.getSection())
                .isDone(todo.getIsDone())
                .createdAt(todo.getCreatedAt())
                .completedAt(todo.getCompletedAt())
                .build();
    }
}
