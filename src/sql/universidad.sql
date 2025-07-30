create database universidad;
-- localhost:8080
create table profesor(
    cedula varchar(10) primary key,
    nombre varchar(50),
    apellido varchar(50),
    oficina smallint,
    telefono integer
);

create table materia(
    codigo smallserial,
    nombre varchar(50),
    creditos smallint
);

create table alumno(
    nombre varchar(50),
    apellido varchar(50),
    cedula varchar(10),
    semestre smallint,
    edad smallint
);

create table curso(
    codigo smallserial,
    nombre varchar(50),
    duracion smallint
);

create table matricula(
    codigo_materia smallint,
    cedula_alumno varchar(10)
);
create table materia_profesor(
    cedula_profesor varchar(10),
    codigo_materia smallint
);
create table alumnos_profesor(
    cedula_profesor varchar(10),
    cedula_alumno varchar(10)
);