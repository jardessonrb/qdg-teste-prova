package com.example.prova.fake;

import com.example.prova.dto.UserCreateRequest;
import com.example.prova.entity.User;

public class UserFake {

    public static UserCreateRequest createValidUser(){
        return UserCreateRequest
                .builder()
                .name("João da Silva")
                .cpf("832.228.130-78")
                .phone("86994112233")
                .build();
    }
}
