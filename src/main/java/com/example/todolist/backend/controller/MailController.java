package com.example.todolist.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todolist.backend.dto.MailRequestDto;
import com.example.todolist.backend.service.EmailService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mail")
@CrossOrigin(origins = "*")
public class MailController {

    private final EmailService emailService;

    @PostMapping("/consult")
    public ResponseEntity<Void> sendConsult(@RequestBody MailRequestDto dto) {
        emailService.sendConsultMail(dto);
        return ResponseEntity.ok().build();
    }
}
