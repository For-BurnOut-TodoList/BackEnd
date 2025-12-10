package com.example.todolist.backend.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.todolist.backend.domain.Praise;
import com.example.todolist.backend.domain.Todo;
import com.example.todolist.backend.dto.PraiseCreateRequestDto;
import com.example.todolist.backend.dto.PraiseResponseDto;
import com.example.todolist.backend.repository.PraiseRepository;
import com.example.todolist.backend.repository.TodoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PraiseService {

    private final PraiseRepository praiseRepository;
    private final TodoRepository todoRepository;

    public PraiseResponseDto createPraise(PraiseCreateRequestDto dto) {
        Todo todo = todoRepository.findById(dto.getTodoId())
                .orElseThrow(() -> new IllegalArgumentException("Todo not found."));

        Praise praise = Praise.builder()
                .todo(todo)
                .praise(dto.getPraise())
                .createdAt(LocalDateTime.now())
                .build();

        Praise savedPraise = praiseRepository.save(praise);
        return toDto(savedPraise);
    }

    public List<PraiseResponseDto> getPraiseByTodo(Long todoId) {
        return praiseRepository.findByTodoId(todoId)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public void deletePraise(Long id) {
        praiseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Praise not found."));
        praiseRepository.deleteById(id);
    }

    private PraiseResponseDto toDto(Praise praise) {
        return PraiseResponseDto.builder()
                .id(praise.getId())
                .todoId(praise.getTodo().getId())
                .praise(praise.getPraise())
                .createdAt(praise.getCreatedAt())
                .build();
    }
}
