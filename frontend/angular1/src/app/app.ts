import { Component, inject, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Encabezado } from "./encabezado/encabezado";
import { Formulario } from "./formulario/formulario";
import { Listado } from "./listado/listado";
import { MyService } from './my-service';

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
