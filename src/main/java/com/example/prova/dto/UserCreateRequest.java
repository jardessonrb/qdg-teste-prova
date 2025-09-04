package com.example.prova.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.hibernate.validator.constraints.br.CPF;

@Builder
public record UserCreateRequest(
    @NotNull(message = "name is required")
    @NotBlank(message = "name cannot be empty")
    String name,

    @CPF(message = "cpf must have a valid value")
    @NotNull(message = "cpf is required")
    @NotBlank(message = "cpf cannot be empty")
    String cpf,

    @NotNull(message = "phone is required")
    @NotBlank(message = "phone cannot be empty")
    String phone
) {}
