import { Component, inject } from '@angular/core';
import { AlumnosApi } from '../../services/alumnos-api';

@Component({
  selector: 'app-alumnos',
  imports: [],
  templateUrl: './alumnos.html',
  styleUrl: './alumnos.css'
})
export class Alumnos {
  private alumnoService = inject(AlumnosApi);

  ngOnInit(): void {
    this.loadAlumno();
  }

  private loadAlumno(): void {
    this.alumnoService.getAllAlumnos().subscribe(alumnos => {
      console.log(alumnos);
    });
  }
}
