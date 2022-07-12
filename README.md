# Implementação de Token JWT com Spring Boot, Spring Security, AUTH0 e JPA | JAVA E POSTGRESQL

Este projeto foi criado a partir da video aula do Canal Expertos Tech no Youtube. 

Segue links abaixo dos vídeos com as explicações de como faz a implementação.

**Vídeo tutorial:** </br>
Parte 1: https://youtu.be/WM8Ty4ITcFc <br>
Parte 2: https://youtu.be/o5h2VennVlE <br>
Parte 3: https://youtu.be/-xjF1rYltAQ <br>
Parte 4: https://youtu.be/1UxEFuDv7nY <br>
Parte 5: https://youtu.be/e3KJA2hHQaw

Fiz o passo a passo para aprender melhor como funciona uma implementação de JWT, Spring Security, AUTH0 e JPA.


## Funcionalidades do microsserviço

#### Login do Usuário
- POST /login

Body:
{
"login": "vanessa",
"password": "1234"
}


#### Listar todos os Usuários
- GET /api/usuario/listarTodos

Headers:
Authorization: Bearer TOKEN