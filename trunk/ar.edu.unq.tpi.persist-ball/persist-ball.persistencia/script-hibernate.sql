alter table Equipo drop foreign key FK7C2D8A5FBB8B157D
alter table Formacion drop foreign key FKE364EE42C37E9A97
alter table FormacionStrategy_posiciones drop foreign key FK2F2C17B2C77AF7C7
alter table Habilidad drop foreign key FK903E9CA676B24DD
alter table Jugador drop foreign key FK180D33A2C37E9A97
alter table PartidoCopa drop foreign key FK931CBCBED243B58B
alter table PartidoCopa drop foreign key FK931CBCBE93136A9C
alter table PartidoCopa drop foreign key FK931CBCBE482F7683
alter table PartidoCopa drop foreign key FK931CBCBE482F0224
alter table PartidoSimple drop foreign key FK59D86AD359C444DC
alter table PartidoSimple drop foreign key FK59D86AD3482F7683
alter table PartidoSimple drop foreign key FK59D86AD3482F0224
alter table Tecnico drop foreign key FKD8B9659C77AF7C7
alter table Titular drop foreign key FK1551C2071732E530
alter table Titular drop foreign key FK1551C207676B24DD
alter table formacion_suplente drop foreign key FK2377F2F51732E530
alter table formacion_suplente drop foreign key FK2377F2F566BE9C44
drop table if exists Equipo
drop table if exists Formacion
drop table if exists FormacionStrategy
drop table if exists FormacionStrategy_posiciones
drop table if exists Habilidad
drop table if exists Jugador
drop table if exists PartidoCopa
drop table if exists PartidoSimple
drop table if exists Tecnico
drop table if exists Titular
drop table if exists formacion_suplente
create table Equipo (id integer not null auto_increment, stateVersion bigint, nombre varchar(255) unique, tecnico_id integer, primary key (id))
create table Formacion (id integer not null auto_increment, stateVersion bigint, equipo_id integer, primary key (id))
create table FormacionStrategy (DTYPE varchar(31) not null, id integer not null auto_increment, stateVersion bigint, primary key (id))
create table FormacionStrategy_posiciones (FormacionStrategy_id integer not null, posicion varchar(255), order_index integer not null, primary key (FormacionStrategy_id, order_index))
create table Habilidad (DTYPE varchar(31) not null, id integer not null auto_increment, stateVersion bigint, posicion varchar(255), valor integer not null, jugador_id integer, primary key (id))
create table Jugador (id integer not null auto_increment, stateVersion bigint, nombre varchar(255), equipo_id integer, primary key (id))
create table PartidoCopa (id integer not null auto_increment, stateVersion bigint, golesPenalesEquipo1 integer not null, golesPenalesEquipo2 integer not null, equipo1_id integer, equipo2_id integer, partidoIda_id integer, partidoVuelta_id integer, primary key (id))
create table PartidoSimple (id integer not null auto_increment, stateVersion bigint, fecha date, golesEquipo1 integer not null, golesEquipo2 integer not null, equipo1_id integer, equipo2_id integer, ganador_id integer, primary key (id))
create table Tecnico (id integer not null auto_increment, stateVersion bigint, nombre varchar(255), formacionStrategy_id integer, primary key (id))
create table Titular (id integer not null auto_increment, stateVersion bigint, posicion varchar(255), jugador_id integer, formacion_id integer, primary key (id))
create table formacion_suplente (Formacion_id integer not null, suplentes_id integer not null)
alter table Equipo add index FK7C2D8A5FBB8B157D (tecnico_id), add constraint FK7C2D8A5FBB8B157D foreign key (tecnico_id) references Tecnico (id)
alter table Formacion add index FKE364EE42C37E9A97 (equipo_id), add constraint FKE364EE42C37E9A97 foreign key (equipo_id) references Equipo (id)
alter table FormacionStrategy_posiciones add index FK2F2C17B2C77AF7C7 (FormacionStrategy_id), add constraint FK2F2C17B2C77AF7C7 foreign key (FormacionStrategy_id) references FormacionStrategy (id)
alter table Habilidad add index FK903E9CA676B24DD (jugador_id), add constraint FK903E9CA676B24DD foreign key (jugador_id) references Jugador (id)
alter table Jugador add index FK180D33A2C37E9A97 (equipo_id), add constraint FK180D33A2C37E9A97 foreign key (equipo_id) references Equipo (id)
alter table PartidoCopa add index FK931CBCBED243B58B (partidoIda_id), add constraint FK931CBCBED243B58B foreign key (partidoIda_id) references PartidoSimple (id)
alter table PartidoCopa add index FK931CBCBE93136A9C (partidoVuelta_id), add constraint FK931CBCBE93136A9C foreign key (partidoVuelta_id) references PartidoSimple (id)
alter table PartidoCopa add index FK931CBCBE482F7683 (equipo2_id), add constraint FK931CBCBE482F7683 foreign key (equipo2_id) references Equipo (id)
alter table PartidoCopa add index FK931CBCBE482F0224 (equipo1_id), add constraint FK931CBCBE482F0224 foreign key (equipo1_id) references Equipo (id)
alter table PartidoSimple add index FK59D86AD359C444DC (ganador_id), add constraint FK59D86AD359C444DC foreign key (ganador_id) references Equipo (id)
alter table PartidoSimple add index FK59D86AD3482F7683 (equipo2_id), add constraint FK59D86AD3482F7683 foreign key (equipo2_id) references Equipo (id)
alter table PartidoSimple add index FK59D86AD3482F0224 (equipo1_id), add constraint FK59D86AD3482F0224 foreign key (equipo1_id) references Equipo (id)
alter table Tecnico add index FKD8B9659C77AF7C7 (formacionStrategy_id), add constraint FKD8B9659C77AF7C7 foreign key (formacionStrategy_id) references FormacionStrategy (id)
alter table Titular add index FK1551C2071732E530 (formacion_id), add constraint FK1551C2071732E530 foreign key (formacion_id) references Formacion (id)
alter table Titular add index FK1551C207676B24DD (jugador_id), add constraint FK1551C207676B24DD foreign key (jugador_id) references Jugador (id)
alter table formacion_suplente add index FK2377F2F51732E530 (Formacion_id), add constraint FK2377F2F51732E530 foreign key (Formacion_id) references Formacion (id)
alter table formacion_suplente add index FK2377F2F566BE9C44 (suplentes_id), add constraint FK2377F2F566BE9C44 foreign key (suplentes_id) references Jugador (id)
