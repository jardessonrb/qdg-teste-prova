package com.example.prova.repository.user;

import com.example.prova.entity.User;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

public class UserSpecificationBuilder {

    private Specification<User> specification;

    public UserSpecificationBuilder(){
        this.specification = Specification.where(null);
    }

    public static UserSpecificationBuilder builder(){
        return new UserSpecificationBuilder();
    }

    public Specification<User> build(){
        return this.specification;
    }

    public UserSpecificationBuilder and(Specification<User> specificationAnd){
        if(Objects.nonNull(specificationAnd)){
            this.specification = Specification.where(this.specification).and(specificationAnd);
        }

        return this;
    }

    public UserSpecificationBuilder or(Specification<User> specificationOr){
        if(Objects.nonNull(specificationOr)){
            this.specification = Specification.where(this.specification).or(specificationOr);
        }

        return this;
    }
}
