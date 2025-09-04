package com.example.prova.shared.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ExceptionDefaultDto {
    private String mensagem;

    private Integer statusCode;

    private String path;

    private List<String> erros;
}