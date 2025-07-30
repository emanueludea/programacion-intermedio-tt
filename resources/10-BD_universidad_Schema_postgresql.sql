-- PostgreSQL 17 version
-- DROP SCHEMA IF EXISTS universidad CASCADE;
-- CREATE SCHEMA universidad;
-- SET search_path TO universidad;

-- Tabla de alumnos
CREATE TABLE Alumno (
  primer_nombre VARCHAR(30) NOT NULL,
  segundo_nombre VARCHAR(30),
  primer_apellido VARCHAR(30) NOT NULL,
  segundo_apellido VARCHAR(30),
  cedula INTEGER NOT NULL,
  edad INTEGER,
  semestre SMALLINT,
  PRIMARY KEY (cedula)
);

-- Tabla carrera
CREATE TABLE Carrera (
  -- codigo SMALLINT NOT NULL GENERATED ALWAYS AS IDENTITY,
  codigo VARCHAR(10) NOT NULL,
  nombre VARCHAR(30),
  duracion SMALLINT,
  PRIMARY KEY (codigo)
);

-- Tabla materia
CREATE TABLE Materia (
  codigo SMALLINT NOT NULL GENERATED ALWAYS AS IDENTITY,
  -- codigo VARCHAR(10) NOT NULL,
  nombre VARCHAR(30),
  credito SMALLINT,
  PRIMARY KEY (codigo)
);

-- Tabla profesor
CREATE TABLE Profesor (
  primer_nombre VARCHAR(30) NOT NULL,
  segundo_nombre VARCHAR(30),
  primer_apellido VARCHAR(30) NOT NULL,
  segundo_apellido VARCHAR(30),
  cedula INTEGER NOT NULL,
  telefono BIGINT NOT NULL,
  oficina SMALLINT NOT NULL,
  PRIMARY KEY (cedula)
);

-- Tablas auxiliares
-- Tabla alumnos por profesor
CREATE TABLE Alumnos_Profesor (
  cc_profesor INTEGER NOT NULL,
  cc_alumno INTEGER NOT NULL,
  CONSTRAINT FK_profesor FOREIGN KEY (cc_profesor) REFERENCES Profesor (cedula),
  CONSTRAINT FK_alumno FOREIGN KEY (cc_alumno) REFERENCES Alumno (cedula),
  PRIMARY KEY (cc_profesor, cc_alumno)
);

-- Tabla profesor por materia
CREATE TABLE Profesor_Materia (
  cc_profesor INTEGER NOT NULL,
  codigo_materia SMALLINT NOT NULL,
  CONSTRAINT FK_profesor1 FOREIGN KEY (cc_profesor) REFERENCES Profesor (cedula),
  CONSTRAINT FK_materia FOREIGN KEY (codigo_materia) REFERENCES Materia (codigo),
  PRIMARY KEY (cc_profesor, codigo_materia)
);

-- Tabla alumnos por materia
CREATE TABLE Alumno_Materia (
  cc_alumno INTEGER NOT NULL,
  codigo_materia SMALLINT NOT NULL,
  CONSTRAINT FK_alumno1 FOREIGN KEY (cc_alumno) REFERENCES Alumno (cedula),
  CONSTRAINT FK_materia1 FOREIGN KEY (codigo_materia) REFERENCES Materia (codigo),
  PRIMARY KEY (cc_alumno, codigo_materia)
);
