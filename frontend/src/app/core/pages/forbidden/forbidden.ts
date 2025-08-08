import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-forbidden',
  imports: [CommonModule, RouterModule],
  template: `
    <div class="forbidden-container">
      <div class="forbidden-card">
        <h2>Acceso Denegado</h2>
        <p>No tienes permiso para acceder a este recurso.</p>
        <p>Por favor, contacta a tu administrador si crees que esto es un error.</p>
        <a routerLink="/" class="btn btn-primary">Regresar al inicio</a>
      </div>
    </div>
  `,
  styleUrl: './forbidden.css'
})
export class Forbidden {

}
