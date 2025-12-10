package com.example.todolist.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todolist.backend.domain.User;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByIdMail(String idMail);
}
