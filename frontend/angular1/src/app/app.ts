import { Component, inject, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Encabezado } from "./domains/alumnos/pages/encabezado/encabezado";
import { Formulario } from "./domains/alumnos/pages/formulario/formulario";
import { MyService } from './my-service';
import { Listado } from './domains/alumnos/pages/listado/listado';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, Encabezado, Formulario, Listado],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('angular1');
  rutaImagen = "public/img1.png";

  private alumnoService = inject(MyService);

  ngOnInit(): void {
    this.loadAlumno();
  }

  private loadAlumno(): void {
    this.alumnoService.getAllAlumnos().subscribe(alumnos => {
      console.log(alumnos);
    });
  }

}
