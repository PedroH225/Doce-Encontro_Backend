# Doce Encontro Backend

## Description
Doce Encontro(Sweet Gathering) is a mobile app for organizing baby showers in a practical and efficient way. It solves common problems like disorganization and communication failure among participants. The app includes notifications of >

## Technologies
- **Java 21** – Programming language used in the backend.
- **Spring Boot** – Framework for building modern Java applications.
- **Spring Web** – Creation of REST APIs.
- **Spring Data JPA** – Data access and persistence using ORM.
- **Spring Security** – Management of application authentication and security.
- **Spring WebSocket** – Support for real-time bidirectional communication.
- **JWT (Auth0 Java JWT)** – Authentication based on JSON Web Tokens.
- **MySQL** – Relational database.
- **Lombok** – Reduction of boilerplate in Java classes (getters, setters, constructors, etc.).
- **Docker Compose** – Orchestration and management of containers for development environments.

## How to install and run the backend

### Prerequisites
- **Docker** installed

### Execution
1. Clone the repository and access the backend folder
    ```bash
    git clone git@github.com:DoceEncontro/Backend.git 
    cd ./Doce-Encontro-Backend
    ```

2. Create the necessary environment variables

    In the project root, create the `.env` file and adjust the values according to your execution environment. An example is available [here](https://github.com/DoceEncontro/Doce-Encontro-Backend/blob/main/.env-example).

3. In the same terminal, run:
    ```bash
    docker-compose up --build 
    ```

## Usage
- [Run the Frontend](https://github.com/DoceEncontro/Frontend)
- Make requests to the backend: [Swagger](http://localhost:8080/swagger-ui/index.html)
