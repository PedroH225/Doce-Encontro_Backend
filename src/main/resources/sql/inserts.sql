INSERT INTO `festora-db`.`usuarios` (id, nome, email, senha) VALUES
('77eea251-9b21-42b1-b9cf-276035b66ed7', 'João Silva', 'joao@hotmail.com', 'senha123'),
('37997bb5-8bba-4707-ae13-6d89b4cdf668', 'Maria Oliveira', 'maria@gmail.com', 'senha456'),
('b634becf-47f9-4c99-a45e-f00f531e29bb', 'Carlos Souza', 'carlos@gmail.com', 'senha789');

INSERT INTO `festora-db`.`enderecos` (id, local, estado, cidade, rua, numero) VALUES
('f8a770e5-dcdf-4a6f-b49f-ce2dd0d47044', 'Rua A, 123', 'SP', 'São Paulo', 'Rua X', 10),
('788c6437-79ce-400d-b72a-8845f2189933', 'Avenida B, 456', 'RJ', 'Rio de Janeiro', 'Avenida Y', 20),
('3450efc5-da79-496c-b3d1-44981b61f27f', 'Rua C, 789', 'MG', 'Belo Horizonte', 'Rua Z', 30);

INSERT INTO `festora-db`.`eventos` (id, titulo, descricao, data, endereco_id, usuario_id) VALUES
('bd5def34-a0c0-447f-bfec-679360657861', 'Evento A', 'Descrição do Evento A', '2025-03-10 18:00:00', 'f8a770e5-dcdf-4a6f-b49f-ce2dd0d47044', '77eea251-9b21-42b1-b9cf-276035b66ed7'),
('5d463b1b-cc33-4e5b-9a30-8e4bab92d9c4', 'Evento B', 'Descrição do Evento B', '2025-03-12 19:00:00', '788c6437-79ce-400d-b72a-8845f2189933', '37997bb5-8bba-4707-ae13-6d89b4cdf668'),
('25da36ea-f881-44ea-a325-54094d96d459', 'Evento C', 'Descrição do Evento C', '2025-03-14 20:00:00', '3450efc5-da79-496c-b3d1-44981b61f27f', 'b634becf-47f9-4c99-a45e-f00f531e29bb');
