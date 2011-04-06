
-- Para importarlo hay q ejecutar: 'mysql < script.sql'

SET FOREIGN_KEY_CHECKS = 0;

DROP DATABASE IF EXISTS `Persistencia`;
CREATE DATABASE IF NOT EXISTS `Persistencia`
  CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `Persistencia`;


-- --------------------------------------------------------
-- Estructura de la tabla `Jugador`
-- --------------------------------------------------------
drop table if exists `Jugador`
create table if not exists Jugador (
	id int NOT NULL AUTO_INCREMENT,
	nombre varchar(100),
	stateVersion bigint(20) NOT NULL default 0,
	PRIMARY KEY(id)
	
) ENGINE=INNODB;


-- --------------------------------------------------------
-- Estructura de la tabla `Habilidad`
-- --------------------------------------------------------
drop table if exists `Habilidad`
create table if not exists Habilidad (
	id int NOT NULL AUTO_INCREMENT,
	position int(2), 
	valor int(2), default NULL
	stateVersion bigint(20) NOT NULL default 0,
	PRIMARY KEY(id)
	
) ENGINE=INNODB;

-- --------------------------------------------------------
-- Estructura de la tabla `JugadorHabilidad`
-- --------------------------------------------------------
drop table if exists `JugadorHabilidad`
create table if not exists JugadorHabilidad (
	jugador_id int NOT NULL,
	habilidad_id int NOT NULL,
	PRIMARY KEY(jugador_id,habilidad_id),
  KEY `FK_JugadorHabilidad_Jugador` (`jugador_id`),
  KEY `FK_JugadorHabilidad_Habilidad` (`habilidad_id`),
  CONSTRAINT `FK_JugadorHabilidad_Jugador` FOREIGN KEY (`jugador_id`) REFERENCES `Jugador` (`id`),
  CONSTRAINT `FK_JugadorHabilidad_Habilidad` FOREIGN KEY (`habilidad_id`) REFERENCES `Habilidad` (`id`)
	
) ENGINE=INNODB;


-- --------------------------------------------------------
-- Estructura de la tabla `HabilidadImpl`
-- --------------------------------------------------------


SET FOREIGN_KEY_CHECKS = 1;
