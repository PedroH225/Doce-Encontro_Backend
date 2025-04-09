INSERT INTO `festora-db`.`usuarios` (id, nome, email, senha) VALUES
('77eea251-9b21-42b1-b9cf-276035b66ed7', 'João Silva', 'joao@example.com', '$2a$10$xkrk1iVWdWmUtvP0XjWzsu/KSKXMuor73lRdnpxvqBczTjVAOxXIG'),
('37997bb5-8bba-4707-ae13-6d89b4cdf668', 'Maria Oliveira', 'maria@example.com', '$2a$10$hP/LHClj.PYP.i5rWRnWSOzeTkvfvUuTrA4smsEenHF5sPeTTK95W'),
('b634becf-47f9-4c99-a45e-f00f531e29bb', 'Carlos Souza', 'carlos@example.com', '$2a$10$mCdgqORL0u5pLy9Y31MSEerJh8gzWnIzc9EyEkocGEWMi50NKR1jC');


INSERT INTO `festora-db`.`eventos` (id, titulo, descricao, data, usuario_id) VALUES
('bd5def34-a0c0-447f-bfec-679360657861', 'Evento A', 'Descrição do Evento A', '2025-03-10 18:00:00', '77eea251-9b21-42b1-b9cf-276035b66ed7'),
('5d463b1b-cc33-4e5b-9a30-8e4bab92d9c4', 'Evento B', 'Descrição do Evento B', '2025-03-12 19:00:00','37997bb5-8bba-4707-ae13-6d89b4cdf668'),
('25da36ea-f881-44ea-a325-54094d96d459', 'Evento C', 'Descrição do Evento C', '2025-03-14 20:00:00', 'b634becf-47f9-4c99-a45e-f00f531e29bb');

INSERT INTO `festora-db`.`enderecos` (id, local, estado, cidade, rua, numero, evento_id) VALUES
('f8a770e5-dcdf-4a6f-b49f-ce2dd0d47044', 'Rua A, 123', 'SP', 'São Paulo', 'Rua X', 10, 'bd5def34-a0c0-447f-bfec-679360657861'),
('788c6437-79ce-400d-b72a-8845f2189933', 'Avenida B, 456', 'RJ', 'Rio de Janeiro', 'Avenida Y', 20, '5d463b1b-cc33-4e5b-9a30-8e4bab92d9c4'),
('3450efc5-da79-496c-b3d1-44981b61f27f', 'Rua C, 789', 'MG', 'Belo Horizonte', 'Rua Z', 30, '25da36ea-f881-44ea-a325-54094d96d459');


-- Arquivos
INSERT INTO `festora-db`.`arquivos` (id, nome, caminho, tipo, evento_id) VALUES
('a1b2c3d4-e5f6-4abc-8def-1234567890aa', 'arquivo1.pdf', '/uploads/arquivo1.pdf', 'pdf', 'bd5def34-a0c0-447f-bfec-679360657861'),
('b2c3d4e5-f6a7-4def-9abc-2345678901bb', 'arquivo2.docx', '/uploads/arquivo2.docx', 'docx', '5d463b1b-cc33-4e5b-9a30-8e4bab92d9c4'),
('c3d4e5f6-a7b8-4abc-0def-3456789012cc', 'arquivo3.jpg', '/uploads/arquivo3.jpg', 'jpg', '25da36ea-f881-44ea-a325-54094d96d459');

-- Imagens (usando os mesmos IDs de arquivos)
INSERT INTO `festora-db`.`imagens` (id, nome, caminho, eventos_id) VALUES
('a1b2c3d4-e5f6-4abc-8def-1234567890aa', 'imagem1.jpg', '/uploads/imagem1.jpg', 'bd5def34-a0c0-447f-bfec-679360657861'),
('b2c3d4e5-f6a7-4def-9abc-2345678901bb', 'imagem2.jpg', '/uploads/imagem2.jpg', '5d463b1b-cc33-4e5b-9a30-8e4bab92d9c4'),
('c3d4e5f6-a7b8-4abc-0def-3456789012cc', 'imagem3.jpg', '/uploads/imagem3.jpg', '25da36ea-f881-44ea-a325-54094d96d459');

-- Notificações
INSERT INTO `festora-db`.`notificacoes` (id, titulo, corpo, data) VALUES
('e4f5g6h7-i8j9-4abc-1def-4567890123dd', 'Bem-vindo', 'Bem-vindo ao Festora!', '2025-03-01 09:00:00'),
('f5g6h7i8-j9k0-4def-2abc-5678901234ee', 'Evento Confirmado', 'Seu evento foi confirmado.', '2025-03-02 10:00:00');

-- Datas Especiais
INSERT INTO `festora-db`.`datas_especiais` (id, data, usuarios_id) VALUES
('11111111-2222-4333-8444-5555555555aa', '2025-12-25 00:00:00', '77eea251-9b21-42b1-b9cf-276035b66ed7'),
('22222222-3333-4444-8555-6666666666bb', '2025-01-01 00:00:00', '37997bb5-8bba-4707-ae13-6d89b4cdf668');

-- Amizades
INSERT INTO `festora-db`.`amizades` (id, usuarios_id, amigo_id, status) VALUES
('33333333-4444-4555-8666-7777777777cc', '77eea251-9b21-42b1-b9cf-276035b66ed7', '37997bb5-8bba-4707-ae13-6d89b4cdf668', 'ACEITO'),
('44444444-5555-4666-8777-8888888888dd', '37997bb5-8bba-4707-ae13-6d89b4cdf668', 'b634becf-47f9-4c99-a45e-f00f531e29bb', 'PENDENTE');

-- Usuario_Evento (usuários participando de eventos além dos seus próprios)
INSERT INTO `festora-db`.`usuario_evento` (usuarios_id, eventos_id) VALUES
('77eea251-9b21-42b1-b9cf-276035b66ed7', '5d463b1b-cc33-4e5b-9a30-8e4bab92d9c4'),
('37997bb5-8bba-4707-ae13-6d89b4cdf668', '25da36ea-f881-44ea-a325-54094d96d459'),
('b634becf-47f9-4c99-a45e-f00f531e29bb', 'bd5def34-a0c0-447f-bfec-679360657861');

-- Notificacao_Usuario (relacionando notificações a usuários)
INSERT INTO `festora-db`.`notificacao_usuario` (notificacao_id, usuario_id) VALUES
('e4f5g6h7-i8j9-4abc-1def-4567890123dd', '77eea251-9b21-42b1-b9cf-276035b66ed7'),
('e4f5g6h7-i8j9-4abc-1def-4567890123dd', '37997bb5-8bba-4707-ae13-6d89b4cdf668'),
('f5g6h7i8-j9k0-4def-2abc-5678901234ee', 'b634becf-47f9-4c99-a45e-f00f531e29bb');

INSERT INTO `festora-db`.`requisitos` (id, titulo, descricao, evento_id) VALUES
('d72f72a0-e2f5-4c7b-9d4a-4d6db0f6495b', 'Picanha', '2kg de pão de alho.', 'bd5def34-a0c0-447f-bfec-679360657861'),
('a2c2c0e1-fad5-4a3e-b836-5f6c9ad5d85c', 'Cerveja', '2L de cerveja.', 'bd5def34-a0c0-447f-bfec-679360657861');

INSERT INTO `festora-db`.`requisitos` (id, titulo, descricao, evento_id) VALUES
('e6f5c85e-89ab-4bfa-97e2-59714d4b27d3', 'Carvão', '1 saco de carvão.', '5d463b1b-cc33-4e5b-9a30-8e4bab92d9c4'),
('b13e17c9-df67-4ed9-92bc-55954d2f7a43', 'Pão de alho', '12 unidades de pão de alho.', '5d463b1b-cc33-4e5b-9a30-8e4bab92d9c4');

INSERT INTO `festora-db`.`requisitos` (id, titulo, descricao, evento_id) VALUES
('b1e9cbb1-ae10-4db1-8086-0cba12dd5935', 'Linguiça', '2kg de linguiça.', '25da36ea-f881-44ea-a325-54094d96d459'),
('e002e576-4bda-4be6-adc5-d6ada04ad105', 'Coração de frango.', '1kg de coração de frango.', '25da36ea-f881-44ea-a325-54094d96d459');

INSERT INTO `festora-db`.`chats` (id, nome, evento_id) VALUES
('a7046e44-3be1-4485-ab7e-e9d04bfba1ad', 'Chat de Evento A', 'bd5def34-a0c0-447f-bfec-679360657861'),
('f1ff21ec-1d8e-40bb-af82-d6ddc7bec4a8', 'Chat de Evento B', '5d463b1b-cc33-4e5b-9a30-8e4bab92d9c4'),
('81ee50e6-648f-4134-95da-3877612e88b9', 'Chat de Evento C', '25da36ea-f881-44ea-a325-54094d96d459');