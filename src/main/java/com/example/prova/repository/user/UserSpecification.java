package com.example.prova.repository.user;

import com.example.prova.entity.User;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

public class UserSpecification {

    public static Specification<User> filterByNameContainsIgnoreCase(String nameFilter){
        return ((root, query, criteriaBuilder) -> {
            if(Objects.isNull(nameFilter)){
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + nameFilter.toLowerCase() + "%");
        });
    }

    public static Specification<User> filterByPhoneContainsNotMask(String phoneFilter){
        return ((root, query, criteriaBuilder) -> {
            if(Objects.isNull(phoneFilter)){
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.like(criteriaBuilder.lower(root.get("phone")), "%" + phoneFilter + "%");
        });
    }

    public static Specification<User> filterByCPFContainsNotMask(String cpfFilter){
        return ((root, query, criteriaBuilder) -> {
            if(Objects.isNull(cpfFilter)){
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.equal(root.get("cpf"), cpfFilter);
        });
    }
}
