package com.example.prova.dto;

import lombok.Builder;

@Builder
public record UserFilter(
        String name,
        String cpf,
        String phone
) {}
