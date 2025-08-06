import { Component, input, output } from '@angular/core';
import { Alumno } from '../../models/alumno';

@Component({
  selector: 'app-listado-alumnos',
  imports: [],
  templateUrl: './listado.html',
  styleUrl: './listado.css'
})
export class Listado {
  alumnos = input<Alumno[]>([]);
  delete = output<Alumno>();
  update = output<Alumno>();

  editarAlumno(alumno: Alumno) {
    console.log("Editar alumno: " + alumno.cedula);
    this.update.emit(alumno);
  }
  eliminarAlumno(alumno: Alumno) {
    console.log("Eliminar alumno: " + alumno.cedula);
    this.delete.emit(alumno);
  }
}
