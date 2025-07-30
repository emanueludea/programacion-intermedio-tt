-- PostgreSQL 17 version - Data insertion
-- SET search_path TO universidad;

INSERT INTO Alumno (primer_nombre, segundo_nombre, primer_apellido, segundo_apellido, cedula, edad, semestre) 
VALUES 
  ('Carlos', NULL, 'Caicedo', 'Rodriguez', 123456, 20, 3),
  ('Pedro', 'Luis', 'Perez', 'Garcia', 123654, 22, 5),
  ('Maria', 'Elena', 'Gonzalez', 'Lopez', 987654, 19, 2),
  ('Ana', 'Sofia', 'Martinez', 'Ruiz', 456789, 21, 4);

INSERT INTO Profesor (primer_nombre, segundo_nombre, primer_apellido, segundo_apellido, cedula, telefono, oficina)
VALUES 
  ('Emanuel', 'Antonio', 'Montoya', 'Gomez', 654321, 2546578, 1),
  ('Sandra', 'Patricia', 'Rivera', 'Castillo', 789123, 3158642, 2),
  ('Jorge', 'Alberto', 'Vargas', 'Moreno', 159753, 3009876, 3),
  ('Claudia', 'Mercedes', 'Herrera', 'Silva', 357951, 3216549, 4);

INSERT INTO Carrera (codigo, nombre, duracion)
VALUES 
  ('CARRERA001', 'Ingeniería de Software', 10),
  ('CARRERA002', 'Ingeniería de Sistemas', 9),
  ('CARRERA003', 'Ciencias de la Computación', 8),
  ('CARRERA004', 'Ingeniería Informática', 10);

INSERT INTO Materia (nombre, credito)
VALUES 
  ('Programación en JAVA', 5),
  ('Base de Datos', 4),
  ('Algoritmos y Estructuras', 5),
  ('Desarrollo Web', 3);
