import { Component } from '@angular/core';

@Component({
  selector: 'app-encabezado-alumnos',
  imports: [],
  templateUrl: './encabezado.html',
  styleUrl: './encabezado.css'
})
export class Encabezado {
  title = 'Sistema de Gestión de Alumnos';
  mostrarTitulo = false;
}
