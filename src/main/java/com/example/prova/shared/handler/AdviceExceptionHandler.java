package com.example.prova.shared.handler;

import com.example.prova.shared.exception.EntityAlreadyExistException;
import com.example.prova.shared.exception.dto.ExceptionDefaultDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;

@RestControllerAdvice
public class AdviceExceptionHandler {

    @ResponseStatus(code = HttpStatus.CONFLICT)
    @ExceptionHandler(EntityAlreadyExistException.class)
    public ExceptionDefaultDto handle(EntityAlreadyExistException exception, HttpServletRequest request){
        return ExceptionDefaultDto
                .builder()
                .mensagem(exception.getMessage())
                .statusCode(HttpStatus.CONFLICT.value())
                .erros(Arrays.asList(exception.getMessage()))
                .path(request.getRequestURI())
                .build();
    }
}
