import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Encabezado } from "./encabezado/encabezado";
import { Formulario } from "./formulario/formulario";
import { Listado } from "./listado/listado";

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, Encabezado, Formulario, Listado],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('angular1');
  rutaImagen = "public/img1.png";
}
