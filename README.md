# Prova – Base de Autenticação (JWT)

Este projeto fornece **apenas a base de autenticação** (JWT + Spring Security + H2). 
O **desafio do candidato** é implementar a **API de consulta paginada de usuários**.

## Como rodar

### Aplicação
```bash
mvn spring-boot:run
```

### Suite de testes
```bash
mvn test
```

Login (gera JWT):
```
POST http://localhost:8080/api/auth/login
Content-Type: application/json

{"username":"pleno","password":"123456"}
```

Use o token retornado para acessar rotas protegidas (ex.: `GET /api/ping`).

## O que o candidato deve implementar

- Entidade `User` (campos: `name`, `cpf`, `phone`).
- Popular a base (via `data.sql` ou `CommandLineRunner`).
- `UserRepository` (JPA + `JpaSpecificationExecutor`).
- `UserService` com filtros dinâmicos (`name` contains/ignore-case; `cpf` por dígitos; `phone` por dígitos/partial).
- `UserController` com endpoint:
  - `GET /api/users?name=&cpf=&phone=&page=&size=&sort=` (protegido por JWT).
- Retornar paginação (`Page<UserDTO>` ou wrapper com metadados).
- **Diferencial**: testes unitários/integrados, documentação da API (OpenAPI/Swagger).

## Observações

- Banco **H2** em memória.
- Usuário seed: `pleno` / senha: `123456`.
- Rotas liberadas: `/api/auth/login`, `/h2-console/**`, `GET /actuator/health`.
- Demais rotas exigem `Authorization: Bearer <token>`.# prova


## Implementados
  - Implementado endpoint ``` GET /api/users ``` com os seguintes parâmetros, retornando um ``` PageResponse ``` que é um DTO para retorno no lugar de Page.
    
    - name - filtro com ``` ilike ``` no name quando enviado
    - phone - filtro com ``` ilike ``` no phone quando enviado
    - cpf - filtro com  ``` equal ``` no cpf do usuário (CPF único na base) quando enviado
   
  - Implementados testes unitários

    - teste unitário para filtro de name
    - teste unitário para filtro de phone
    - teste unitário para filtro de cpf
    - teste unitário para todos os filtros
    
  - Implementado specification
    - implementado filtros através de specifications, usado no repository com ```JpaSpecificationExecutor<User>```
    - implementado ````UserSpecificationBuilder```` usando o design pattern builder para construção de filtros dinâmicos como no exemplo abaixo
      - ``` 
        UserSpecificationBuilder
                .builder()
                .and(UserSpecification.filterByNameContainsIgnoreCase(filter.name()))
                .and(UserSpecification.filterByPhoneContainsNotMask(Objects.nonNull(filter.phone()) ? filter.phone().replaceAll("\\D", "") : null))
                .and(UserSpecification.filterByCPFContainsNotMask(Objects.nonNull(filter.cpf()) ? filter.cpf().replaceAll("\\D", "") : null))
                .build()
        ```
  - Implementado ```` handlder ```` para interceptar e padronizar o retorno de exceptions na API usando o ``` @RestControllerAdvice ```
  - Implementado ``` DataInitializerUsers ``` para criação de usuários iniciais quando subir a API.
