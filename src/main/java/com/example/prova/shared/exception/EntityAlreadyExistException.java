package com.example.prova.shared.exception;

public class EntityAlreadyExistException extends RuntimeException {
    public EntityAlreadyExistException(String mensagem){
        super(mensagem);
    }
}

