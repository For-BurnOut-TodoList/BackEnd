package com.example.todolist.backend.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.todolist.backend.domain.User;
import com.example.todolist.backend.dto.LoginRequestDTO;
import com.example.todolist.backend.dto.SignUpRequestDTO;
import com.example.todolist.backend.dto.UserResponseDTO;
import com.example.todolist.backend.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDTO signUp(SignUpRequestDTO dto) {
        userRepository.findByIdMail(dto.getIdMail())
                .ifPresent(existing -> {
                    throw new IllegalArgumentException("Email already exists.");
                });

        String encodedPassword = passwordEncoder.encode(dto.getPassword());

        User user = User.builder()
                .idMail(dto.getIdMail())
                .name(dto.getName())
                .password(encodedPassword)
                .build();

        User savedUser = userRepository.save(user);
        return toResponse(savedUser);
    }

    public UserResponseDTO login(LoginRequestDTO dto) {
        User user = userRepository.findByIdMail(dto.getIdMail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email."));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid password.");
        }

        return toResponse(user);
    }

    private UserResponseDTO toResponse(User user) {
        return UserResponseDTO.builder()
                .idMail(user.getIdMail())
                .name(user.getName())
                .build();
    }
}
