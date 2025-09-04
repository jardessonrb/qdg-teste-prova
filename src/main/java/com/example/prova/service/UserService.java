package com.example.prova.service;

import com.example.prova.dto.PageResponse;
import com.example.prova.dto.UserCreateRequest;
import com.example.prova.dto.UserCreatedResponse;
import com.example.prova.dto.UserFilter;

import org.springframework.data.domain.Pageable;

public interface UserService {
    PageResponse<UserCreatedResponse> pagedUserSearch(UserFilter filter, Pageable pagination);
    UserCreatedResponse createUser(UserCreateRequest userForm);
}
