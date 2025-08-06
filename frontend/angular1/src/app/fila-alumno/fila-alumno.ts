import { Component, input, output } from '@angular/core';
import { Alumno } from '../alumno';

@Component({
  selector: 'app-fila-alumno',
  imports: [],
  templateUrl: './fila-alumno.html',
  styleUrl: './fila-alumno.css'
})
export class FilaAlumno {
  alumno = input<Alumno>();

  salida1 = output<number>();

  editarAlumno(cedula: number) {
    console.log("Editar alumno: " + cedula);
    this.salida1.emit(cedula);
  }

  eliminarAlumno(cedula: number) {
    console.log("Eliminar alumno: " + cedula);
  }
}
