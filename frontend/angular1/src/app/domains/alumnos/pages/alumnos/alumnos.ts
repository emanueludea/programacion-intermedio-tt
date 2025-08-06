import { Component, inject } from '@angular/core';
import { AlumnosApi } from '../../services/alumnos-api';
import { Encabezado } from "../encabezado/encabezado";
import { Formulario } from "../formulario/formulario";
import { Listado } from "../listado/listado";

@Component({
  selector: 'app-alumnos',
  imports: [Encabezado, Formulario, Listado],
  templateUrl: './alumnos.html',
  styleUrl: './alumnos.css'
})
export class Alumnos {
  private alumnoService = inject(AlumnosApi);
  rutaImagen = "img1.png";
  ngOnInit(): void {
    this.loadAlumno();
  }

  private loadAlumno(): void {
    this.alumnoService.getAllAlumnos().subscribe(alumnos => {
      console.log(alumnos);
    });
  }
}
