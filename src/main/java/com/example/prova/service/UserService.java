package com.example.prova.service;

import com.example.prova.dto.UserCreatedResponse;
import com.example.prova.dto.UserFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Page<UserCreatedResponse> pagedUserSearch(UserFilter filter, Pageable pagination);
}
