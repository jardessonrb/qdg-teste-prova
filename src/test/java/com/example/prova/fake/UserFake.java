package com.example.prova.fake;

import com.example.prova.dto.UserCreateRequest;

import java.util.Arrays;
import java.util.List;

public class UserFake {

    public static UserCreateRequest createValidUser(){
        return UserCreateRequest
                .builder()
                .name("João da Silva")
                .cpf("832.228.130-78")
                .phone("86994112233")
                .build();
    }

    public static List<UserCreateRequest> createValidsUsers(){
       return Arrays.asList(
               UserCreateRequest.builder().name("João da Silva").cpf("832.228.130-78").phone("86994112233").build(),
               UserCreateRequest.builder().name("Maria Aparecida").cpf("390.533.447-05").phone("85996595856").build(),
               UserCreateRequest.builder().name("José Ribeiro").cpf("295.379.148-04").phone("88993254859").build(),
               UserCreateRequest.builder().name("Otávio Miranda").cpf("478.160.228-06").phone("86995444786").build(),
               UserCreateRequest.builder().name("Ana Oliveira Santos").cpf("135.792.468-70").phone("88996332211").build(),
               UserCreateRequest.builder().name("Agatha Ferreira").cpf("852.963.741-02").phone("85996895856").build(),
               UserCreateRequest.builder().name("Ravi Oliveira").cpf("746.971.314-00").phone("85996592255").build(),
               UserCreateRequest.builder().name("Maria Ysis Silva").cpf("123.456.789-09").phone("81885663355").build(),
               UserCreateRequest.builder().name("João José").cpf("987.654.321-00").phone("81997445566").build(),
               UserCreateRequest.builder().name("Raimundo Moura").cpf("741.852.963-09").phone("84556998855").build(),
               UserCreateRequest.builder().name("Guilherme Oliveira").cpf("159.753.486-20").phone("859965947766").build(),
               UserCreateRequest.builder().name("Rosiana Sosy").cpf("321.654.987-01").phone("87889663355").build(),
               UserCreateRequest.builder().name("Mariana Aveel").cpf("258.369.147-00").phone("11997558899").build()
       );
    }
}
