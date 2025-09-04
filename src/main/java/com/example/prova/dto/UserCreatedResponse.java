package com.example.prova.dto;

import com.example.prova.entity.User;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserCreatedResponse(
        UUID id,
        String name,
        String cpf,
        String phone,
        LocalDateTime createdAt
) {
    public UserCreatedResponse(User model){
        this(model.getUuid(), model.getName(), model.getCpf(), model.getPhone(), model.getCreatedAt());
    }
}