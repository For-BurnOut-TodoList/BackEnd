package com.example.todolist.backend.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PraiseResponseDto {
    private Long id;
    private Long todoId;
    private String praise;
    private LocalDateTime createdAt;
}
