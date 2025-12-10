package com.example.todolist.backend.dto;

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
public class TodoUpdateRequestDto {
    private Long id;
    private String todo;
    private String section;
    private Boolean isDone;
}
