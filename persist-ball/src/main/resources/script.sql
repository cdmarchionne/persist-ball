
-- Para importarlo hay q ejecutar el script CargarDB.bat

-- ----------------------------------------------------------------------

SET FOREIGN_KEY_CHECKS = 0;

DROP DATABASE IF EXISTS `Persistencia`;
CREATE DATABASE IF NOT EXISTS `Persistencia`
  CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `Persistencia`;

-- ----------------------------------------------------------------------

--     TABLAS

-- --------------------------------------------------------
-- Estructura de la tabla `Equipo`
-- --------------------------------------------------------
DROP TABLE IF EXISTS `Equipo`;
CREATE TABLE IF NOT EXISTS `Equipo` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`stateVersion` BIGINT(20) NULL,
	`nombre` VARCHAR(255) NULL,
	`tecnico_id` INT(11) NULL,
	PRIMARY KEY (`id`),
	KEY `FK_Equipo_Tecnico` (`tecnico_id`),
	CONSTRAINT `FK_Equipo_Tecnico` FOREIGN KEY (`tecnico_id`) REFERENCES `Tecnico` (`id`)
)
ENGINE = INNODB;

-- --------------------------------------------------------
-- Estructura de la tabla `Formacion` y sus Relaciones
-- --------------------------------------------------------
DROP TABLE IF EXISTS `Formacion`;
CREATE TABLE IF NOT EXISTS  `Formacion` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`stateVersion` BIGINT(20) NULL,
	`equipo_id` INT(11) NULL,
	PRIMARY KEY (`id`),
	KEY `FK_Formacion_Equipo` (`equipo_id`),
	CONSTRAINT `FK_Formacion_Equipo` FOREIGN KEY (`equipo_id`) REFERENCES `Equipo` (`id`)
)
ENGINE = INNODB;

-- --------------------------------------------------------
-- Relacion de los Jugadores Suplentes en la Formacion
-- --------------------------------------------------------
DROP TABLE IF EXISTS `Formacion_suplente`;
CREATE TABLE IF NOT EXISTS `Formacion_suplente` (
	`formacion_id` INT(11) NOT NULL,
	`suplente_id` INT(11) NOT NULL,
	PRIMARY KEY (`suplentes_id`, `formacion_id`),
	KEY `FK_FormacionJugador_Equipo` (`formacion_id`),
	KEY `FK_FormacionJugador_Jugadores` (`suplentes_id`),
	CONSTRAINT `FK_FormacionJugador_Equipo` FOREIGN KEY (`formacion_id`) REFERENCES `Formacion` (`id`),
	CONSTRAINT `FK_FormacionJugador_Jugadores` FOREIGN KEY (`suplentes_id`) REFERENCES `Jugador` (`id`)
)
ENGINE = INNODB;

-- --------------------------------------------------------
-- Estructura de la tabla `FormacionStrategy` y sus referencias
-- --------------------------------------------------------
DROP TABLE IF EXISTS `FormacionStrategy`;
CREATE TABLE IF NOT EXISTS  `FormacionStrategy` (
	`DTYPE` VARCHAR(31) NOT NULL,
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`stateVersion` BIGINT(20) NULL,
	PRIMARY KEY (`id`)
)
ENGINE = INNODB;

-- --------------------------------------------------------
-- Relacion de los Posiciones en la FormacionStrategy
-- --------------------------------------------------------
DROP TABLE IF EXISTS `FormacionStrategy_posiciones`;
CREATE TABLE IF NOT EXISTS `FormacionStrategy_posiciones` (
	`FormacionStrategy_id` INT(11) NOT NULL,
	`posicion` VARCHAR(255) NULL,
	`order_index` INT(11) NOT NULL,
	PRIMARY KEY (`FormacionStrategy_id`, `order_index`),
	KEY `FK_FSP_FormacionStrategy` (`FormacionStrategy_id`),
	CONSTRAINT `FK_FSP_FormacionStrategy` FOREIGN KEY (`FormacionStrategy_id`) REFERENCES `FormacionStrategy` (`id`)
)
ENGINE = INNODB;

-- --------------------------------------------------------
-- Estructura de la tabla `Habilidad`
-- --------------------------------------------------------
DROP TABLE IF EXISTS `Habilidad`;
CREATE TABLE IF NOT EXISTS `Habilidad` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`position` VARCHAR(255) NULL,
	`valor` INT(2) NULL,
	`stateVersion` BIGINT(20) NOT NULL DEFAULT '0',
	`jugador_id` INT(11) NOT NULL,
	`DTYPE` VARCHAR(31) NOT NULL,
	PRIMARY KEY (`id`),
	KEY `FK_Habilidad_Jugador` (`jugador_id`),
	CONSTRAINT `FK_Habilidad_Jugador` FOREIGN KEY (`jugador_id`) REFERENCES `Jugador` (`id`)
) ENGINE=INNODB;

-- --------------------------------------------------------
-- Estructura de la tabla `Jugador`
-- --------------------------------------------------------
DROP TABLE IF EXISTS `Jugador`;
CREATE TABLE IF NOT EXISTS `Jugador` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`nombre` VARCHAR(100),
	`stateVersion` bigint(20) NOT NULL default 0,
	`equipo_id` INT(11) NOT NULL,
	PRIMARY KEY (`id`)
	KEY `FK_Jugador_Equipo` (`equipo_id`),
	CONSTRAINT `FK_Jugador_Equipo` FOREIGN KEY (`equipo_id`) REFERENCES `Equipo` (`id`)
) ENGINE=INNODB;

-- --------------------------------------------------------
-- Estructura de la tabla `Tecnico`
-- --------------------------------------------------------
DROP TABLE IF EXISTS `Tecnico`;
CREATE TABLE IF NOT EXISTS  `Tecnico` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`stateVersion` BIGINT(20) NULL,
	`nombre` VARCHAR(255) NULL,
	`formacionStrategy_id` INT(11) NULL,
	PRIMARY KEY (`id`),
	KEY `FK_Tecnico_FormacionStrategy` (`formacionStrategy_id`),
	CONSTRAINT `FK_Tecnico_FormacionStrategy` FOREIGN KEY (`formacionStrategy_id`) REFERENCES `FormacionStrategy` (`id`)
)
ENGINE = INNODB;

-- --------------------------------------------------------
-- Estructura de la tabla `Titular`
-- --------------------------------------------------------
DROP TABLE IF EXISTS `Titular`;
CREATE TABLE IF NOT EXISTS  `Titular` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`stateVersion` BIGINT(20) NULL,
	`posicion` VARCHAR(255) NULL,
	`jugador_id` INT(11) NULL,
	`formacion_id` INT(11) NULL,
	PRIMARY KEY (`id`),
	KEY `FK_Titular_Jugador` (`jugador_id`),
	KEY `FK_Titular_Formacion` (`formacion_id`),
	CONSTRAINT `FK_Titular_Jugador` FOREIGN KEY (`jugador_id`) REFERENCES `Jugador` (`id`)
	CONSTRAINT `FK_Titular_Formacion` FOREIGN KEY (`formacion_id`) REFERENCES `Formacion` (`id`)
)
ENGINE = INNODB;


SET FOREIGN_KEY_CHECKS = 1;

-- ----------------------------------------------------------------------
