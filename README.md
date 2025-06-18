# Doce Encontro Backend
## Descrição
O Doce Encontro é um app mobile para organizar chás de bebê de forma prática e eficiente. Ele resolve problemas comuns como desorganização e falha na comunicação entre participantes. O app inclui notificações de tarefas, calendário interativo, integração com Google Maps, recomendações com IA, chatbot e chats para convidados. Com design intuitivo e tecnologia moderna, o Doce Encontro facilita todo o processo, tornando o planejamento simples, acessível e livre de estresse.

## Tecnologias
- **Java 21** – Linguagem de programação utilizada no backend.
- **Spring Boot** – Framework para construção de aplicações Java modernas.
- **Spring Web** – Criação de APIs REST.
- **Spring Data JPA** – Acesso e persistência de dados utilizando ORM.
- **Spring Security** – Gerenciamento de autenticação e segurança da aplicação.
- **Spring WebSocket** – Suporte à comunicação bidirecional em tempo real.
- **JWT (Auth0 Java JWT)** – Autenticação baseada em tokens JSON Web Tokens.
- **MySQL** – Banco de dados relacional.
- **Lombok** – Redução de boilerplate em classes Java (getters, setters, construtores etc.).
- **Docker Compose** – Orquestração e gerenciamento de contêineres para ambientes de desenvolvimento.

## Como instalar e executar o backend

### Pré-requisitos
- **Docker** instalado

### Execução
1. Clone o repositório e acesse a pasta do backend
```bash
git clone git@github.com:DoceEncontro/Backend.git 
cd ./Doce-Encontro-Backend
```

2. No mesmo terminal, execute:
```bash
docker-compose up --build 
```

## Uso
- [Execute o Frontend](https://github.com/DoceEncontro/Frontend)
- Fazer requisições ao backend: [Swagger](http://localhost:8080/swagger-ui/index.html)
