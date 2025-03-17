-- -----------------------------------------------------
-- Schema festora-db
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `festora-db`;

-- -----------------------------------------------------
-- Schema festora-db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `festora-db`;
USE `festora-db`;

-- -----------------------------------------------------
-- Table `festora-db`.`usuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `festora-db`.`usuarios` (
  `id` VARCHAR(36) NOT NULL,
  `nome` VARCHAR(50) NOT NULL,
  `email` VARCHAR(100) UNIQUE NOT NULL,
  `senha` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `festora-db`.`enderecos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `festora-db`.`enderecos` (
  `id` VARCHAR(36) NOT NULL,
  `local` VARCHAR(100) NULL,
  `estado` VARCHAR(45) NOT NULL,
  `cidade` VARCHAR(50) NOT NULL,
  `rua` VARCHAR(100) NOT NULL,
  `numero` INT NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `festora-db`.`eventos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `festora-db`.`eventos` (
  `id` VARCHAR(36) NOT NULL,
  `titulo` VARCHAR(100) NOT NULL,
  `descricao` TEXT NULL,
  `tipo` VARCHAR(30) NULL,
  `data` DATETIME NOT NULL,
  `endereco_id` VARCHAR(36) NOT NULL,
  `usuario_id` VARCHAR(36) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`endereco_id`) REFERENCES `festora-db`.`enderecos`(`id`) ON DELETE CASCADE,
  FOREIGN KEY (`usuario_id`) REFERENCES `festora-db`.`usuarios`(`id`) ON DELETE CASCADE
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `festora-db`.`arquivos` (
    id VARCHAR(36) PRIMARY KEY NOT NULL,
    nome VARCHAR(255) NOT NULL,
    caminho VARCHAR(255) NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    evento_id VARCHAR(36) NOT NULL,
    FOREIGN KEY (evento_id) REFERENCES `festora-db`.`eventos`(id) ON DELETE CASCADE
);

-- -----------------------------------------------------
-- Table `festora-db`.`imagens`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `festora-db`.`imagens` (
  `id` VARCHAR(36) NOT NULL,
  `nome` VARCHAR(100) NOT NULL,
  `caminho` LONGTEXT NOT NULL,
  `eventos_id` VARCHAR(36) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (id) REFERENCES `festora-db`.`arquivos`(id) ON DELETE CASCADE
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `festora-db`.`notificacoes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `festora-db`.`notificacoes` (
  `id` VARCHAR(36) NOT NULL,
  `titulo` VARCHAR(100) NULL,
  `corpo` LONGTEXT NULL,
  `data` DATETIME NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `festora-db`.`datas_especiais`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `festora-db`.`datas_especiais` (
  `id` VARCHAR(36) NOT NULL,
  `data` DATETIME NOT NULL,
  `usuarios_id` VARCHAR(36) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`usuarios_id`) REFERENCES `festora-db`.`usuarios`(`id`) ON DELETE CASCADE
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `festora-db`.`amizades`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `festora-db`.`amizades` (
  `id` VARCHAR(36) NOT NULL,
  `usuarios_id` VARCHAR(36) NOT NULL,
  `amigo_id` VARCHAR(36) NOT NULL,
  `status` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`usuarios_id`) REFERENCES `festora-db`.`usuarios`(`id`) ON DELETE CASCADE,
  FOREIGN KEY (`amigo_id`) REFERENCES `festora-db`.`usuarios`(`id`) ON DELETE CASCADE
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `festora-db`.`usuario_evento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `festora-db`.`usuario_evento` (
  `usuarios_id` VARCHAR(36) NOT NULL,
  `eventos_id` VARCHAR(36) NOT NULL,
  FOREIGN KEY (`usuarios_id`) REFERENCES `festora-db`.`usuarios`(`id`) ON DELETE CASCADE,
  FOREIGN KEY (`eventos_id`) REFERENCES `festora-db`.`eventos`(`id`) ON DELETE CASCADE
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `festora-db`.`notificacao_usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `festora-db`.`notificacao_usuario` (
  `notificacao_id` VARCHAR(36) NOT NULL,
  `usuario_id` VARCHAR(36) NOT NULL,
  FOREIGN KEY (`usuario_id`) REFERENCES `festora-db`.`usuarios`(`id`) ON DELETE CASCADE,
  FOREIGN KEY (`notificacao_id`) REFERENCES `festora-db`.`notificacoes`(`id`) ON DELETE CASCADE
) ENGINE = InnoDB;