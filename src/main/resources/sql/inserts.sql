INSERT INTO `doce-encontro-db`.`usuarios` (id, nome, email, senha) VALUES
('77eea251-9b21-42b1-b9cf-276035b66ed7', 'João Silva', 'joao@example.com', '$2a$10$xkrk1iVWdWmUtvP0XjWzsu/KSKXMuor73lRdnpxvqBczTjVAOxXIG'),
('37997bb5-8bba-4707-ae13-6d89b4cdf668', 'Maria Oliveira', 'pedrodguimaraes20@gmail.com', '$2a$10$hP/LHClj.PYP.i5rWRnWSOzeTkvfvUuTrA4smsEenHF5sPeTTK95W'),
('b634becf-47f9-4c99-a45e-f00f531e29bb', 'Carlos Souza', 'carlos@example.com', '$2a$10$mCdgqORL0u5pLy9Y31MSEerJh8gzWnIzc9EyEkocGEWMi50NKR1jC'),
('91aa1c11-bb22-4dd3-8ecc-123456789001', 'Lucas Pereira', 'lucas@example.com', '$2a$10$abcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdab'),
('92bb2d22-cc33-4ee4-9fdd-234567890012', 'Ana Costa', 'ana@example.com', '$2a$10$abcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdab'),
('93cc3e33-dd44-4ff5-a0ee-345678901023', 'Felipe Lima', 'felipe@example.com', '$2a$10$abcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdab');


INSERT INTO `doce-encontro-db`.`eventos` (id, titulo, descricao, data, tipo, usuario_id, ativo) VALUES
('bd5def34-a0c0-447f-bfec-679360657861', 'Chá de Bebê do Miguel', 'Venha celebrar a chegada do nosso pequeno Miguel com muito carinho e diversão!', '2025-12-10 18:00:00', 'BEBE', '77eea251-9b21-42b1-b9cf-276035b66ed7', 1),
('5d463b1b-cc33-4e5b-9a30-8e4bab92d9c4', 'Revelação da Maria ou João?', 'Prepare-se para um momento mágico: vamos descobrir juntos se vem uma princesinha ou um príncipe!', '2025-10-12 19:00:00', 'REVELACAO', '37997bb5-8bba-4707-ae13-6d89b4cdf668', 1),
('25da36ea-f881-44ea-a325-54094d96d459', 'Chá de Fraldas da Alice', 'Vamos ajudar os papais da Alice a encher o estoque de fraldas com muito amor e alegria!', '2025-08-14 20:00:00', 'FRALDAS', 'b634becf-47f9-4c99-a45e-f00f531e29bb', 1);

INSERT INTO `doce-encontro-db`.`enderecos` (id, local, estado, cidade, rua, numero, evento_id) VALUES
('f8a770e5-dcdf-4a6f-b49f-ce2dd0d47044', 'Rua A, 123', 'SP', 'São Paulo', 'Rua X', 10, 'bd5def34-a0c0-447f-bfec-679360657861'),
('788c6437-79ce-400d-b72a-8845f2189933', 'Avenida B, 456', 'RJ', 'Rio de Janeiro', 'Avenida Y', 20, '5d463b1b-cc33-4e5b-9a30-8e4bab92d9c4'),
('3450efc5-da79-496c-b3d1-44981b61f27f', 'Rua C, 789', 'MG', 'Belo Horizonte', 'Rua Z', 30, '25da36ea-f881-44ea-a325-54094d96d459');

INSERT INTO `doce-encontro-db`.`arquivos` (id, nome, caminho, tipo, evento_id) VALUES
('a1b2c3d4-e5f6-4abc-8def-1234567890aa', 'arquivo1.pdf', '/uploads/arquivo1.pdf', 'pdf', 'bd5def34-a0c0-447f-bfec-679360657861'),
('b2c3d4e5-f6a7-4def-9abc-2345678901bb', 'arquivo2.docx', '/uploads/arquivo2.docx', 'docx', '5d463b1b-cc33-4e5b-9a30-8e4bab92d9c4'),
('c3d4e5f6-a7b8-4abc-0def-3456789012cc', 'arquivo3.jpg', '/uploads/arquivo3.jpg', 'jpg', '25da36ea-f881-44ea-a325-54094d96d459');

-- Imagens (usando os mesmos IDs de arquivos)
INSERT INTO `doce-encontro-db`.`imagens` (id, nome, caminho, eventos_id) VALUES
('a1b2c3d4-e5f6-4abc-8def-1234567890aa', 'imagem1.jpg', '/uploads/imagem1.jpg', 'bd5def34-a0c0-447f-bfec-679360657861'),
('b2c3d4e5-f6a7-4def-9abc-2345678901bb', 'imagem2.jpg', '/uploads/imagem2.jpg', '5d463b1b-cc33-4e5b-9a30-8e4bab92d9c4'),
('c3d4e5f6-a7b8-4abc-0def-3456789012cc', 'imagem3.jpg', '/uploads/imagem3.jpg', '25da36ea-f881-44ea-a325-54094d96d459');

-- Notificações
INSERT INTO `doce-encontro-db`.`notificacoes` (id, titulo, corpo, data, icone) VALUES
('e4f5g6h7-i8j9-4abc-1def-4567890123dd', 'Bem-vindo', 'Bem-vindo ao Festora!', '2025-03-01 09:00:00', 'SUCCESS'),
('f5g6h7i8-j9k0-4def-2abc-5678901234ee', 'Evento Confirmado', 'Seu evento foi confirmado.', '2025-03-02 10:00:00', 'SUCCESS');

-- Datas Especiais
INSERT INTO `doce-encontro-db`.`datas_especiais` (id, data, usuarios_id) VALUES
('11111111-2222-4333-8444-5555555555aa', '2025-12-25 00:00:00', '77eea251-9b21-42b1-b9cf-276035b66ed7'),
('22222222-3333-4444-8555-6666666666bb', '2025-01-01 00:00:00', '37997bb5-8bba-4707-ae13-6d89b4cdf668');

-- Amizades
INSERT INTO `doce-encontro-db`.`amizades` (id, usuarios_id, amigo_id, status) VALUES
('33333333-4444-4555-8666-7777777777cc', '77eea251-9b21-42b1-b9cf-276035b66ed7', '37997bb5-8bba-4707-ae13-6d89b4cdf668', 'ACEITO'),
('444444c4-5555-4666-8777-8888888888dd', '37997bb5-8bba-4707-ae13-6d89b4cdf668', 'b634becf-47f9-4c99-a45e-f00f531e29bb', 'PENDENTE'),
('333333a3-4444-4555-8666-7777777777cc', '77eea251-9b21-42b1-b9cf-276035b66ed7', 'b634becf-47f9-4c99-a45e-f00f531e29bb', 'ACEITO'),
('55555555-aaaa-4bbb-8ccc-9999999999ee', '77eea251-9b21-42b1-b9cf-276035b66ed7', '91aa1c11-bb22-4dd3-8ecc-123456789001', 'PENDENTE'),
('66666666-bbbb-4ccc-8ddd-aaaaaaaaaaff', '92bb2d22-cc33-4ee4-9fdd-234567890012', '77eea251-9b21-42b1-b9cf-276035b66ed7', 'PENDENTE'),
('77777777-cccc-4ddd-8eee-bbbbbbbbbb00', '77eea251-9b21-42b1-b9cf-276035b66ed7', '93cc3e33-dd44-4ff5-a0ee-345678901023', 'ACEITO');

-- Usuario_Evento (usuários participando de eventos além dos seus próprios)
INSERT INTO `doce-encontro-db`.`usuario_evento` (usuarios_id, eventos_id) VALUES
('77eea251-9b21-42b1-b9cf-276035b66ed7', '5d463b1b-cc33-4e5b-9a30-8e4bab92d9c4'),
('37997bb5-8bba-4707-ae13-6d89b4cdf668', '25da36ea-f881-44ea-a325-54094d96d459'),
('b634becf-47f9-4c99-a45e-f00f531e29bb', 'bd5def34-a0c0-447f-bfec-679360657861'),

('77eea251-9b21-42b1-b9cf-276035b66ed7', 'bd5def34-a0c0-447f-bfec-679360657861'),
('37997bb5-8bba-4707-ae13-6d89b4cdf668', '5d463b1b-cc33-4e5b-9a30-8e4bab92d9c4'),
('b634becf-47f9-4c99-a45e-f00f531e29bb', '25da36ea-f881-44ea-a325-54094d96d459');


-- Notificacao_Usuario (relacionando notificações a usuários)
INSERT INTO `doce-encontro-db`.`notificacao_usuario` (notificacao_id, usuario_id) VALUES
('e4f5g6h7-i8j9-4abc-1def-4567890123dd', '77eea251-9b21-42b1-b9cf-276035b66ed7'),
('e4f5g6h7-i8j9-4abc-1def-4567890123dd', '37997bb5-8bba-4707-ae13-6d89b4cdf668'),
('f5g6h7i8-j9k0-4def-2abc-5678901234ee', 'b634becf-47f9-4c99-a45e-f00f531e29bb');

-- Evento 1: Chá de Fraldas (evento_id: bd5def34-a0c0-447f-bfec-679360657861)
INSERT INTO `doce-encontro-db`.`requisitos` (id, titulo, descricao, evento_id) VALUES
('d72f72a0-e2f5-4c7b-9d4a-4d6db0f6495b', 'Fraldas Pampers M', 'Pacote com pelo menos 20 unidades.', '25da36ea-f881-44ea-a325-54094d96d459'),
('a2c2c0e1-fad5-4a3e-b836-5f6c9ad5d85c', 'Lenços Umedecidos', 'Pacote com no mínimo 100 unidades.', '25da36ea-f881-44ea-a325-54094d96d459');

-- Evento 2: Chá Revelação (evento_id: 5d463b1b-cc33-4e5b-9a30-8e4bab92d9c4)
INSERT INTO `doce-encontro-db`.`requisitos` (id, titulo, descricao, evento_id) VALUES
('e6f5c85e-89ab-4bfa-97e2-59714d4b27d3', 'Balões Rosa e Azul', 'Pacote de balões para decoração temática.', '5d463b1b-cc33-4e5b-9a30-8e4bab92d9c4'),
('b13e17c9-df67-4ed9-92bc-55954d2f7a43', 'Bolo Temático', 'Bolo com recheio que revela o sexo do bebê.', '5d463b1b-cc33-4e5b-9a30-8e4bab92d9c4');

-- Evento 3: Chá de Bebê (evento_id: 25da36ea-f881-44ea-a325-54094d96d459)
INSERT INTO `doce-encontro-db`.`requisitos` (id, titulo, descricao, evento_id) VALUES
('b1e9cbb1-ae10-4db1-8086-0cba12dd5935', 'Mamadeira', 'Mamadeira para recém-nascido.', 'bd5def34-a0c0-447f-bfec-679360657861'),
('e002e576-4bda-4be6-adc5-d6ada04ad105', 'Roupinhas RN', 'Conjunto de 3 peças tamanho recém-nascido.', 'bd5def34-a0c0-447f-bfec-679360657861');

INSERT INTO `doce-encontro-db`.`chats` (id, nome, evento_id) VALUES
('a7046e44-3be1-4485-ab7e-e9d04bfba1ad', 'Chat de Evento A', 'bd5def34-a0c0-447f-bfec-679360657861'),
('f1ff21ec-1d8e-40bb-af82-d6ddc7bec4a8', 'Chat de Evento B', '5d463b1b-cc33-4e5b-9a30-8e4bab92d9c4'),
('81ee50e6-648f-4134-95da-3877612e88b9', 'Chat de Evento C', '25da36ea-f881-44ea-a325-54094d96d459');

INSERT INTO `doce-encontro-db`.`convites` (id, titulo, descricao, evento_id) VALUES
('48a54446-ebca-4ffd-bc7c-1aed856087c7', 'Evento C convite', 'descrição', '25da36ea-f881-44ea-a325-54094d96d459'),
('2d3c7d71-d48d-4cff-b859-74b35a9684e3', 'Evento B convite', 'descrição', '5d463b1b-cc33-4e5b-9a30-8e4bab92d9c4'),
('2d0a6297-1b92-4b1a-b4b6-acb23d830183', 'Evento A convite', 'descrição', 'bd5def34-a0c0-447f-bfec-679360657861');

