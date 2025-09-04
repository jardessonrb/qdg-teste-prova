package com.example.prova.service;

import com.example.prova.dto.UserCreateRequest;
import com.example.prova.dto.UserCreatedResponse;
import com.example.prova.fake.UserFake;
import com.example.prova.repository.user.UserRepository;
import jakarta.persistence.EntityExistsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Criação de usuário válido")
    void createUserWithDataValidSuccess() {
        UserCreateRequest validUser = UserFake.createValidUser();

        UserCreatedResponse userCreated = assertDoesNotThrow(
                () -> userService.createUser(validUser)
        );

        assertNotNull(userCreated.id());
        assertNotNull(userCreated.createdAt());
        assertEquals(validUser.name(), userCreated.name());
        assertEquals(validUser.cpf().replaceAll("\\D", ""), userCreated.cpf());
        assertEquals(validUser.phone().replaceAll("\\D", ""), userCreated.phone());
    }

    @Test
    @DisplayName("Erro ao criar mais de um usuário com o mesmo CPF")
    void createUserWithCPFDuplicatedError() {
        UserCreateRequest validUser = UserFake.createValidUser();
        String messageErro = "Exist user with cpf "+validUser.cpf();

        assertDoesNotThrow(
                () -> userService.createUser(validUser)
        );

        EntityExistsException exception = assertThrows(EntityExistsException.class,
                () -> userService.createUser(validUser)
        );

        assertEquals(messageErro, exception.getMessage());
    }
}
