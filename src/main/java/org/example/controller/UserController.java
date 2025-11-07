package org.example.controller;

import org.example.entity.User;

import java.time.LocalDateTime;

public interface UserController {
    User createUser(String name, String email, Integer age, LocalDateTime created_at);
    User readUser(int id);
    void updateUser(int id, Integer age);
    void deleteUser(int id);
}
