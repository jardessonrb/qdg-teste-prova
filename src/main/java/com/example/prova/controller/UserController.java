package com.example.prova.controller;

import com.example.prova.dto.PageResponse;
import com.example.prova.dto.UserCreatedResponse;
import com.example.prova.dto.UserFilter;
import com.example.prova.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<PageResponse<UserCreatedResponse>> findUsers(
            @RequestParam(name = "name", required = false) String nameFilter,
            @RequestParam(name = "cpf",  required = false) String cpfFilter,
            @RequestParam(name = "phone",required = false) String phoneFilter,
            @PageableDefault(sort = "name", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable pagination
    ){

        return ResponseEntity.ok(userService.pagedUserSearch(new UserFilter(nameFilter, cpfFilter, phoneFilter), pagination));
    }
}
