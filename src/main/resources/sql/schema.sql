-- -----------------------------------------------------
-- Schema doce-encontro-db
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `doce-encontro-db`;

-- -----------------------------------------------------
-- Schema doce-encontro-db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `doce-encontro-db`;
USE `doce-encontro-db`;

-- -----------------------------------------------------
-- Table `doce-encontro-db`.`usuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `doce-encontro-db`.`usuarios` (
  `id` VARCHAR(36) NOT NULL,
  `nome` VARCHAR(50) NOT NULL,
  `email` VARCHAR(100) UNIQUE NOT NULL,
  `senha` VARCHAR(100) NOT NULL,
  `criado_em` DATETIME DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `doce-encontro-db`.`eventos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `doce-encontro-db`.`eventos` (
  `id` VARCHAR(36) NOT NULL,
  `titulo` VARCHAR(100) NOT NULL,
  `descricao` TEXT NULL,
  `tipo` VARCHAR(30) NULL,
  `data` DATETIME NOT NULL,
  `usuario_id` VARCHAR(36) NOT NULL,
  `ativo` BOOLEAN NOT NULL DEFAULT TRUE,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`usuario_id`) REFERENCES `doce-encontro-db`.`usuarios`(`id`) ON DELETE CASCADE
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `doce-encontro-db`.`enderecos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `doce-encontro-db`.`enderecos` (
  `id` VARCHAR(36) NOT NULL,
  `local` VARCHAR(100) NOT NULL,
  `estado` VARCHAR(45) NOT NULL,
  `cidade` VARCHAR(50) NOT NULL,
  `rua` VARCHAR(100) NOT NULL,
  `numero` INT NOT NULL,
  `evento_id` VARCHAR(36) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`evento_id`) REFERENCES `doce-encontro-db`.`eventos`(`id`) ON DELETE CASCADE
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `doce-encontro-db`.`arquivos` (
    id VARCHAR(36) PRIMARY KEY NOT NULL,
    nome VARCHAR(255) NOT NULL,
    caminho VARCHAR(255) NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    evento_id VARCHAR(36) NOT NULL,
    FOREIGN KEY (evento_id) REFERENCES `doce-encontro-db`.`eventos`(id) ON DELETE CASCADE
);

-- -----------------------------------------------------
-- Table `doce-encontro-db`.`imagens`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `doce-encontro-db`.`imagens` (
  `id` VARCHAR(36) NOT NULL,
  `nome` VARCHAR(100) NOT NULL,
  `caminho` LONGTEXT NOT NULL,
  `eventos_id` VARCHAR(36) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (id) REFERENCES `doce-encontro-db`.`arquivos`(id) ON DELETE CASCADE
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `doce-encontro-db`.`notificacoes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `doce-encontro-db`.`notificacoes` (
  `id` VARCHAR(36) NOT NULL,
  `titulo` VARCHAR(100) NULL,
  `corpo` LONGTEXT NULL,
  `data` DATETIME NULL,
  `icone` VARCHAR(20) DEFAULT 'NOTIFICATION',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `doce-encontro-db`.`datas_especiais`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `doce-encontro-db`.`datas_especiais` (
  `id` VARCHAR(36) NOT NULL,
  `data` DATETIME NOT NULL,
  `usuarios_id` VARCHAR(36) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`usuarios_id`) REFERENCES `doce-encontro-db`.`usuarios`(`id`) ON DELETE CASCADE
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `doce-encontro-db`.`amizades`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `doce-encontro-db`.`amizades` (
  `id` VARCHAR(36) NOT NULL,
  `usuarios_id` VARCHAR(36),
  `amigo_id` VARCHAR(36),
  `status` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`usuarios_id`) REFERENCES `doce-encontro-db`.`usuarios`(`id`) ON DELETE CASCADE,
  FOREIGN KEY (`amigo_id`) REFERENCES `doce-encontro-db`.`usuarios`(`id`) ON DELETE CASCADE
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `doce-encontro-db`.`usuario_evento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `doce-encontro-db`.`usuario_evento` (
  `usuarios_id` VARCHAR(36) NOT NULL,
  `eventos_id` VARCHAR(36) NOT NULL,
  FOREIGN KEY (`usuarios_id`) REFERENCES `doce-encontro-db`.`usuarios`(`id`) ON DELETE CASCADE,
  FOREIGN KEY (`eventos_id`) REFERENCES `doce-encontro-db`.`eventos`(`id`) ON DELETE CASCADE
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `doce-encontro-db`.`notificacao_usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `doce-encontro-db`.`notificacao_usuario` (
  `notificacao_id` VARCHAR(36) NOT NULL,
  `usuario_id` VARCHAR(36) NOT NULL,
  FOREIGN KEY (`usuario_id`) REFERENCES `doce-encontro-db`.`usuarios`(`id`) ON DELETE CASCADE,
  FOREIGN KEY (`notificacao_id`) REFERENCES `doce-encontro-db`.`notificacoes`(`id`) ON DELETE CASCADE
) ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `doce-encontro-db`.`requisitos` (
	`id` VARCHAR(36) PRIMARY KEY NOT NULL,
	`titulo` VARCHAR(100) NOT NULL,
	`descricao` VARCHAR(200) NOT NULL,
	`evento_id` VARCHAR(36) NOT NULL,
	 FOREIGN KEY (`evento_id`) REFERENCES `doce-encontro-db`.`eventos`(`id`) ON DELETE CASCADE
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `doce-encontro-db`.`requisito_usuario` (
  `requisito_id` VARCHAR(36) NOT NULL,
  `usuario_id` VARCHAR(36) NOT NULL,
  FOREIGN KEY (`usuario_id`) REFERENCES `doce-encontro-db`.`usuarios`(`id`) ON DELETE CASCADE,
  FOREIGN KEY (`requisito_id`) REFERENCES `doce-encontro-db`.`requisitos`(`id`) ON DELETE CASCADE,
  CONSTRAINT `unique_requisito_usuario` UNIQUE (`requisito_id`, `usuario_id`)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `doce-encontro-db`.`convites` (
	`id` VARCHAR(36) PRIMARY KEY NOT NULL,
	`titulo` VARCHAR(100) NOT NULL,
	`descricao` VARCHAR(200) NOT NULL,
	`evento_id` VARCHAR(36) NOT NULL,
	 FOREIGN KEY (`evento_id`) REFERENCES `doce-encontro-db`.`eventos`(`id`) ON DELETE CASCADE
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `doce-encontro-db`.`convite_usuario` (
  `convite_id` VARCHAR(36) NOT NULL,
  `usuario_id` VARCHAR(36) NOT NULL,
  FOREIGN KEY (`usuario_id`) REFERENCES `doce-encontro-db`.`usuarios`(`id`) ON DELETE CASCADE,
  FOREIGN KEY (`convite_id`) REFERENCES `doce-encontro-db`.`convites`(`id`) ON DELETE CASCADE,
  CONSTRAINT `unique_requisito_usuario` UNIQUE (`convite_id`, `usuario_id`)
) ENGINE = InnoDB;

CREATE TABLE `doce-encontro-db`.`chats` (
    `id` VARCHAR(36) PRIMARY KEY,
    `nome` VARCHAR(100) NOT NULL,
    `evento_id` VARCHAR(36),
    FOREIGN KEY (`evento_id`) REFERENCES `doce-encontro-db`.`eventos`(`id`) ON DELETE CASCADE
) ENGINE = InnoDB;


CREATE TABLE `doce-encontro-db`.`chat_usuario` (
    `chat_id` VARCHAR(36) NOT NULL,
    `usuario_id` VARCHAR(36) NOT NULL,
    FOREIGN KEY (`chat_id`) REFERENCES `doce-encontro-db`.`chats`(`id`) ON DELETE CASCADE,
    FOREIGN KEY (`usuario_id`) REFERENCES `doce-encontro-db`.`usuarios`(`id`) ON DELETE CASCADE
) ENGINE = InnoDB;


CREATE TABLE `doce-encontro-db`.`mensagens` (
    `id` VARCHAR(36) PRIMARY KEY,
    `conteudo` TEXT NOT NULL,
	`data_envio` TIMESTAMP NOT NULL,
	`chat_id` VARCHAR(36) NOT NULL,
    `usuario_id` VARCHAR(36) NOT NULL,
    FOREIGN KEY (`chat_id`) REFERENCES `doce-encontro-db`.`chats`(id) ON DELETE CASCADE,
    FOREIGN KEY (`usuario_id`) REFERENCES `doce-encontro-db`.`usuarios`(id) ON DELETE CASCADE
) ENGINE = InnoDB;
