import { Component, input } from '@angular/core';
import { FilaAlumno } from "../fila-alumno/fila-alumno";
import { Alumno } from '../../models/alumno';

@Component({
  selector: 'app-listado-alumnos',
  imports: [FilaAlumno],
  templateUrl: './listado.html',
  styleUrl: './listado.css'
})
export class Listado {
  alumnos = input<Alumno[]>([]);
}
