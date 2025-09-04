package com.example.prova.service;

import com.example.prova.dto.UserCreateRequest;
import com.example.prova.dto.UserCreatedResponse;
import com.example.prova.dto.UserFilter;
import com.example.prova.fake.UserFake;
import com.example.prova.repository.user.UserRepository;
import com.example.prova.shared.exception.EntityAlreadyExistException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

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

        EntityAlreadyExistException exception = assertThrows(EntityAlreadyExistException.class,
                () -> userService.createUser(validUser)
        );

        assertEquals(messageErro, exception.getMessage());
    }

    @Test
    @DisplayName("Listagem de Users usando o filtro de name simples apenas por name")
    void listingUsersByNameSuccess() {
        List<UserCreateRequest> usersRequest = UserFake.createValidsUsers();
        String nameFilter = "silva";
        int amountExpected = 2;
        Pageable paginationSimple = PageRequest.of(0, 20);

        UserFilter userFilter = UserFilter
                .builder()
                .name(nameFilter)
                .build();
        for(var userRequest : usersRequest){
            assertDoesNotThrow(() -> userService.createUser(userRequest));
        }

        Page<UserCreatedResponse> users = assertDoesNotThrow(() -> userService.pagedUserSearch(userFilter, paginationSimple));

        assertEquals(amountExpected, users.getTotalElements());
    }

    @Test
    @DisplayName("Listagem de Users usando o filtro de name ordenado por name")
    void listingUsersByNameOrderedSuccess() {
        List<UserCreateRequest> usersRequest = UserFake.createValidsUsers();
        String nameFilter = "oliveira";
        int amountExpected = 3;
        String firstNameExpected = "Ana Oliveira Santos";
        String cpfExpected = "13579246870";
        Pageable paginationOrdered = PageRequest.of(0, 20, Sort.by(Sort.Direction.ASC, "name"));

        UserFilter userFilter = UserFilter
                .builder()
                .name(nameFilter)
                .build();
        for(var userRequest : usersRequest){
            assertDoesNotThrow(() -> userService.createUser(userRequest));
        }

        Page<UserCreatedResponse> users = assertDoesNotThrow(() -> userService.pagedUserSearch(userFilter, paginationOrdered));
        UserCreatedResponse user = users.getContent().get(0);

        assertEquals(amountExpected, users.getTotalElements());
        assertEquals(firstNameExpected, user.name());
        assertEquals(cpfExpected, user.cpf());
    }

    @Test
    @DisplayName("Listagem de Users usando o filtro de cpf simples apenas por cpf")
    void listingUsersByCPFSuccess() {
        List<UserCreateRequest> usersRequest = UserFake.createValidsUsers();
        String cpfFilter = "83222813078";
        int amountExpected = 1;
        Pageable paginationSimple = PageRequest.of(0, 20);

        UserFilter userFilter = UserFilter
                .builder()
                .cpf(cpfFilter)
                .build();
        for(var userRequest : usersRequest){
            assertDoesNotThrow(() -> userService.createUser(userRequest));
        }

        Page<UserCreatedResponse> users = assertDoesNotThrow(() -> userService.pagedUserSearch(userFilter, paginationSimple));
        UserCreatedResponse user = users.getContent().get(0);

        assertEquals(amountExpected, users.getTotalElements());
        assertEquals(cpfFilter, user.cpf());
    }

    @Test
    @DisplayName("Listagem de Users usando o filtro de phone simples usando uma parte do phone ordenado")
    void listingUsersByPhonePartialOrderedSuccess() {
        List<UserCreateRequest> usersRequest = UserFake.createValidsUsers();
        String phoneFilter = "8599659";
        String cpfExpected = "15975348620";
        int amountExpected = 3;
        Pageable paginationSimple = PageRequest.of(0, 20, Sort.by(Sort.Direction.ASC, "name"));

        UserFilter userFilter = UserFilter
                .builder()
                .phone(phoneFilter)
                .build();
        for(var userRequest : usersRequest){
            assertDoesNotThrow(() -> userService.createUser(userRequest));
        }

        Page<UserCreatedResponse> users = assertDoesNotThrow(() -> userService.pagedUserSearch(userFilter, paginationSimple));
        UserCreatedResponse user = users.getContent().get(0);
        assertEquals(amountExpected, users.getTotalElements());
        assertEquals(cpfExpected, user.cpf());
    }

    @Test
    @DisplayName("Listagem de Users usando dois parâmetros(name, phone) nos filtros")
    void listingUsersByNameAndPhoneFiltersSuccess() {
        List<UserCreateRequest> usersRequest = UserFake.createValidsUsers();
        String phoneFilter = "8599659";
        String nameFilter = "Oliveira";
        int amountByPhoneExpected = 3;
        int amountByPhoneAndNameExpected = 2;
        Pageable paginationSimple = PageRequest.of(0, 20, Sort.by(Sort.Direction.ASC, "name"));

        UserFilter userByPhoneFilter = UserFilter
                .builder()
                .phone(phoneFilter)
                .build();

        UserFilter userByPhoneAndNameFilter = UserFilter
                .builder()
                .phone(phoneFilter)
                .name(nameFilter)
                .build();

        for(var userRequest : usersRequest){
            assertDoesNotThrow(() -> userService.createUser(userRequest));
        }

        Page<UserCreatedResponse> usersByPhone = assertDoesNotThrow(() -> userService.pagedUserSearch(userByPhoneFilter, paginationSimple));
        Page<UserCreatedResponse> usersByPhoneAndName = assertDoesNotThrow(() -> userService.pagedUserSearch(userByPhoneAndNameFilter, paginationSimple));

        assertEquals(amountByPhoneExpected, usersByPhone.getTotalElements());
        assertEquals(amountByPhoneAndNameExpected, usersByPhoneAndName.getTotalElements());
    }

    @Test
    @DisplayName("Listagem de Users usando todos os filtros")
    void listingUsersByAllParametersFiltersSuccess() {
        List<UserCreateRequest> usersRequest = UserFake.createValidsUsers();
        String phoneFilter = "8599659";
        String nameFilter = "Oliveira";
        String cpfFilter = "159.753.486-20";

        int amountByPhoneExpected = 3;
        int amountByPhoneAndNameExpected = 2;
        int amountByAllParametersExpected = 1;
        Pageable paginationSimple = PageRequest.of(0, 20, Sort.by(Sort.Direction.ASC, "name"));

        UserFilter userByPhoneFilter = UserFilter
                .builder()
                .phone(phoneFilter)
                .build();

        UserFilter userByPhoneAndNameFilter = UserFilter
                .builder()
                .phone(phoneFilter)
                .name(nameFilter)
                .build();

        UserFilter userByAllParametersFilter = UserFilter
                .builder()
                .phone(phoneFilter)
                .name(nameFilter)
                .cpf(cpfFilter)
                .build();

        for(var userRequest : usersRequest){
            assertDoesNotThrow(() -> userService.createUser(userRequest));
        }

        Page<UserCreatedResponse> usersByPhone = assertDoesNotThrow(() -> userService.pagedUserSearch(userByPhoneFilter, paginationSimple));
        Page<UserCreatedResponse> usersByPhoneAndName = assertDoesNotThrow(() -> userService.pagedUserSearch(userByPhoneAndNameFilter, paginationSimple));
        Page<UserCreatedResponse> usersByAllParameters = assertDoesNotThrow(() -> userService.pagedUserSearch(userByAllParametersFilter, paginationSimple));

        assertEquals(amountByPhoneExpected, usersByPhone.getTotalElements());
        assertEquals(amountByPhoneAndNameExpected, usersByPhoneAndName.getTotalElements());
        assertEquals(amountByAllParametersExpected, usersByAllParameters.getTotalElements());
    }
}
