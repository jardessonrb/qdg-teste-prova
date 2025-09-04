package com.example.prova.dto;

import org.hibernate.validator.constraints.UUID;

import java.time.LocalDateTime;

public record UserCreatedResponse(
        UUID id,
        String name,
        String cpf,
        String phone,
        LocalDateTime createdAt
) {}