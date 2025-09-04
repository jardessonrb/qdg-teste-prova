package com.example.prova.service.impl;

import com.example.prova.dto.UserCreatedResponse;
import com.example.prova.dto.UserFilter;
import com.example.prova.entity.User;
import com.example.prova.repository.user.UserRepository;
import com.example.prova.repository.user.UserSpecification;
import com.example.prova.repository.user.UserSpecificationBuilder;
import com.example.prova.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Page<UserCreatedResponse> pagedUserSearch(UserFilter filter, Pageable pagination) {
        Specification<User> specification = UserSpecificationBuilder
                .builder()
                .and(UserSpecification.filterByNameContainsIgnoreCase(filter.name()))
                .and(UserSpecification.filterByPhoneContainsNotMask(Objects.nonNull(filter.phone()) ? filter.phone().replaceAll("\\D", "") : null))
                .and(UserSpecification.filterByCPFContainsNotMask(Objects.nonNull(filter.cpf()) ? filter.cpf().replaceAll("\\D", "") : null))
                .build();

        Page<User> usersPage = userRepository.findAll(specification, pagination);

        return usersPage.map(UserCreatedResponse::new);
    }
}
