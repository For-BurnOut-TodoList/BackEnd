package com.example.todolist.backend.controller;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todolist.backend.dto.PraiseCreateRequestDto;
import com.example.todolist.backend.dto.PraiseResponseDto;
import com.example.todolist.backend.service.PraiseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/praise")
@RequiredArgsConstructor
public class PraiseController {

    private final PraiseService praiseService;

    @PostMapping
    public ResponseEntity<PraiseResponseDto> createPraise(@RequestBody PraiseCreateRequestDto dto) {
        try {
            PraiseResponseDto response = praiseService.createPraise(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{todoId}")
    public ResponseEntity<List<PraiseResponseDto>> getPraiseByTodo(@PathVariable Long todoId) {
        try {
            List<PraiseResponseDto> responses = praiseService.getPraiseByTodo(todoId);
            return ResponseEntity.ok(responses);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePraise(@PathVariable Long id) {
        try {
            praiseService.deletePraise(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
