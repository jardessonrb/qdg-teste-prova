# Prova – Base de Autenticação (JWT)

Este projeto fornece **apenas a base de autenticação** (JWT + Spring Security + H2). 
O **desafio do candidato** é implementar a **API de consulta paginada de usuários**.

## Como rodar

```bash
mvn spring-boot:run
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
