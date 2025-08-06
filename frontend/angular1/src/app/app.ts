import { Component, inject, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Encabezado } from "./domains/alumnos/pages/encabezado/encabezado";

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, Encabezado],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('angular1');
  rutaImagen = "public/img1.png";

}
