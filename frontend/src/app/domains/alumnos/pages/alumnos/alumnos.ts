import { Component, inject, signal } from '@angular/core';
import { AlumnosApi } from '../../services/alumnos-api';
import { Encabezado } from "../encabezado/encabezado";
import { Formulario } from "../formulario/formulario";
import { Listado } from "../listado/listado";
import { Alumno } from '../../models/alumno';

@Component({
  selector: 'app-alumnos',
  imports: [Encabezado, Formulario, Listado],
  templateUrl: './alumnos.html',
  styleUrl: './alumnos.css'
})
export class Alumnos {
  private alumnoService = inject(AlumnosApi);

  alumnos = signal<Alumno[]>([]);
  rutaImagen = "img1.png";
  ngOnInit(): void {
    this.loadAlumno();
  }

  private loadAlumno(): void {
    this.alumnoService.getAllAlumnos().subscribe(alumnos => {
      this.alumnos.set(alumnos);
      console.log(this.alumnos());
    });
  }

  eliminarAlumno(alumno: Alumno): void {
    this.alumnoService.deleteAlumno(alumno.cedula).subscribe(() => {
      this.alumnos.set(this.alumnos().filter(a => a.cedula !== alumno.cedula));
      console.log(`Alumno con cédula ${alumno.cedula} eliminado.`);
    });
  }
  editarAlumno(alumno: Alumno): void {
    console.log(`Editar alumno con cédula ${alumno.cedula}`);
    this.alumnoService.updateAlumno(alumno.cedula, alumno).subscribe(updatedAlumnos => {
      this.alumnos.set(this.alumnos().map(a => a.cedula === updatedAlumnos.cedula ? updatedAlumnos : a));
      console.log(`Alumno con cédula ${updatedAlumnos.cedula} actualizado.`);
    });
  }
}
