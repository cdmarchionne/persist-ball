-- Para importarlo hay q ejecutar el script CargarDB.bat

-- ----------------------------------------------------------------------

SET FOREIGN_KEY_CHECKS = 0;

DROP DATABASE IF EXISTS `persistencia`;
CREATE DATABASE IF NOT EXISTS `persistencia`
  CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `persistencia`;

-- ----------------------------------------------------------------------

--     TABLAS

-- --------------------------------------------------------
-- Estructura de la tabla `Equipo`
-- --------------------------------------------------------
DROP TABLE IF EXISTS `Equipo`;
CREATE TABLE IF NOT EXISTS `Equipo` (
	`id` INT(11)  AUTO_INCREMENT,
	`stateVersion` BIGINT(20) DEFAULT '0' NOT NULL,
	`nombre` VARCHAR(255) NULL,
	`tecnico_id` INT(11),
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
	`id` INT(11) AUTO_INCREMENT,
	`stateVersion` BIGINT(20) DEFAULT '0' NOT NULL,
	`equipo_id` INT(11),
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
	`formacion_id` INT(11),
	`suplentes_id` INT(11),
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
	`DTYPE` VARCHAR(31) ,
	`id` INT(11)  AUTO_INCREMENT,
	`stateVersion` BIGINT(20) DEFAULT '0' NOT NULL,
	PRIMARY KEY (`id`)
)
ENGINE = INNODB;

-- --------------------------------------------------------
-- Relacion de los Posiciones en la FormacionStrategy
-- --------------------------------------------------------
DROP TABLE IF EXISTS `FormacionStrategy_posiciones`;
CREATE TABLE IF NOT EXISTS `FormacionStrategy_posiciones` (
	`FormacionStrategy_id` INT(11),
	`posicion` VARCHAR(255) NULL,
	`order_index` INT(11) ,
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
	`id` INT(11) AUTO_INCREMENT,
	`posicion` VARCHAR(255) NULL,
	`valor` INT(2) NULL,
	`stateVersion` BIGINT(20) DEFAULT '0' NOT NULL,
	`jugador_id` INT(11),
	`DTYPE` VARCHAR(31),
	PRIMARY KEY (`id`),
	KEY `FK_Habilidad_Jugador` (`jugador_id`),
	CONSTRAINT `FK_Habilidad_Jugador` FOREIGN KEY (`jugador_id`) REFERENCES `Jugador` (`id`)
) ENGINE=INNODB;

-- --------------------------------------------------------
-- Estructura de la tabla `Jugador`
-- --------------------------------------------------------
DROP TABLE IF EXISTS `Jugador`;
CREATE TABLE IF NOT EXISTS `Jugador` (
	`id` INT(11) AUTO_INCREMENT,
	`nombre` VARCHAR(100),
	`stateVersion` bigint(20) DEFAULT '0' NOT NULL,
	`equipo_id` INT(11) ,
	PRIMARY KEY (`id`),
	KEY `FK_Jugador_Equipo` (`equipo_id`),
	CONSTRAINT `FK_Jugador_Equipo` FOREIGN KEY (`equipo_id`) REFERENCES `Equipo` (`id`)
) ENGINE=INNODB;

-- --------------------------------------------------------
-- Estructura de la tabla `PartidoSimple`
-- --------------------------------------------------------
DROP TABLE IF EXISTS `PartidoSimple`;
CREATE TABLE IF NOT EXISTS  `PartidoSimple` (
	`id` INT(11) AUTO_INCREMENT,
	`stateVersion` BIGINT(20) DEFAULT '0' NOT NULL,
	`fecha` DATE NOT NULL,
	`golesEquipo1` INT(11),
	`golesEquipo2` INT(11),
	`equipo1_id` INT(11),
	`equipo2_id` INT(11),
	`ganador_id` INT(11),
	PRIMARY KEY (`id`),
	KEY `FK_PartidoSimple_Equipo1` (`equipo1_id`),
	KEY `FK_PartidoSimple_Equipo2` (`equipo2_id`),
	-- KEY `Buqueda_Ganador` (`ganador_id`),
	CONSTRAINT `FK_PartidoSimple_Equipo1` FOREIGN KEY (`equipo1_id`) REFERENCES `Equipo` (`id`),
	CONSTRAINT `FK_PartidoSimple_Equipo2` FOREIGN KEY (`equipo2_id`) REFERENCES `Equipo` (`id`)
)
ENGINE = INNODB;

-- --------------------------------------------------------
-- Estructura de la tabla `PartidoCopa`
-- --------------------------------------------------------
DROP TABLE IF EXISTS `PartidoCopa`;
CREATE TABLE IF NOT EXISTS  `PartidoCopa` (
	`id` INT(11)  AUTO_INCREMENT,
	`stateVersion` BIGINT(20) DEFAULT '0' NOT NULL,
	`golesPenalesEquipo1` INT(11),
	`golesPenalesEquipo2` INT(11),
	`equipo1_id` INT(11),
	`equipo2_id` INT(11),
	`partidoIda_id` INT(11),
	`partidoVuelta_id` INT(11),
	`ganador_id` INT(11),
	PRIMARY KEY (`id`),
	KEY `FK_PartidoCopa_Equipo1` (`equipo1_id`),
	KEY `FK_PartidoCopa_Equipo2` (`equipo2_id`),
	KEY `FK_PartidoCopa_PartidoIda` (`partidoIda_id`),
	KEY `FK_PartidoCopa_PartidoVueta` (`partidoVuelta_id`),
	KEY `FK_PartidoCopa_EquipoGanador` (`ganador_id`),
	CONSTRAINT `FK_PartidoCopa_Equipo1` FOREIGN KEY (`equipo1_id`) REFERENCES `Equipo` (`id`),
	CONSTRAINT `FK_PartidoCopa_Equipo2` FOREIGN KEY (`equipo2_id`) REFERENCES `Equipo` (`id`),
	CONSTRAINT `FK_PartidoCopa_PartidoIda` FOREIGN KEY (`partidoIda_id`) REFERENCES `PartidoSimple` (`id`),
	CONSTRAINT `FK_PartidoCopa_EquipoGanador` FOREIGN KEY (`ganador_id`) REFERENCES `Equipo` (`id`),
	CONSTRAINT `FK_PartidoCopa_PartidoVueta` FOREIGN KEY (`partidoVuelta_id`) REFERENCES `PartidoSimple` (`id`)
)
ENGINE = INNODB;

-- --------------------------------------------------------
-- Estructura de la tabla `Tecnico`
-- --------------------------------------------------------
DROP TABLE IF EXISTS `Tecnico`;
CREATE TABLE IF NOT EXISTS  `Tecnico` (
	`id` INT(11)  AUTO_INCREMENT,
	`stateVersion` BIGINT(20) DEFAULT '0' NOT NULL,
	`nombre` VARCHAR(255) NULL,
	`formacionStrategy_id` INT(11),
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
	`id` INT(11)  AUTO_INCREMENT,
	`stateVersion` BIGINT(20) DEFAULT '0' NOT NULL,
	`posicion` VARCHAR(255) NULL,
	`jugador_id` INT(11),
	`formacion_id` INT(11),
	PRIMARY KEY (`id`),
	KEY `FK_Titular_Jugador` (`jugador_id`),
	KEY `FK_Titular_Formacion` (`formacion_id`),
	CONSTRAINT `FK_Titular_Jugador` FOREIGN KEY (`jugador_id`) REFERENCES `Jugador` (`id`),
	CONSTRAINT `FK_Titular_Formacion` FOREIGN KEY (`formacion_id`) REFERENCES `Formacion` (`id`)
)
ENGINE = INNODB;


SET FOREIGN_KEY_CHECKS = 1;

-- ----------------------------------------------------------------------
