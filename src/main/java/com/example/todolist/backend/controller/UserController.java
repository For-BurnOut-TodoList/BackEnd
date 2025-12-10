package com.example.todolist.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todolist.backend.dto.LoginRequestDTO;
import com.example.todolist.backend.dto.SignUpRequestDTO;
import com.example.todolist.backend.dto.UserResponseDTO;
import com.example.todolist.backend.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDTO> signUp(@RequestBody SignUpRequestDTO dto) {
        try {
            UserResponseDTO response = userService.signUp(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@RequestBody LoginRequestDTO dto) {
        try {
            UserResponseDTO response = userService.login(dto);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
