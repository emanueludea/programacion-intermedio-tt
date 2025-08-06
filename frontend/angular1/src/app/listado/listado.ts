import { Component } from '@angular/core';
import { FilaAlumno } from "../fila-alumno/fila-alumno";
import { Alumno } from '../alumno';

@Component({
  selector: 'app-listado',
  imports: [FilaAlumno],
  templateUrl: './listado.html',
  styleUrl: './listado.css'
})
export class Listado {
  alumnos: Alumno[] = [
    {
      cedula: 123456,
      primerNombre: 'Juan',
      segundoNombre: 'Carlos',
      primerApellido: 'Pérez',
      segundoApellido: 'Gómez',
      edad: 20,
      semestre: 3
    },
    {
      cedula: 543456,
      primerNombre: 'Andres',
      segundoNombre: 'Panlo',
      primerApellido: 'Cañizales',
      segundoApellido: 'Gómez',
      edad: 20,
      semestre: 1
    }
  ];

  salida1Activa(cedula: number){
    console.log("Salida 1 activa: " + cedula);
  }
}
