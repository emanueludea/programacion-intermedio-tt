import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-forbidden',
  imports: [CommonModule, RouterModule],
  template: `
    <div class="container-fluid d-flex justify-content-center align-items-center min-vh-100 bg-light">
      <div class="card shadow-lg" style="max-width: 500px; width: 100%;">
        <div class="card-body text-center p-5">
          <div class="mb-4">
            <i class="bi bi-shield-exclamation text-danger" style="font-size: 4rem;"></i>
          </div>
          <h2 class="card-title text-danger mb-3">Acceso Denegado</h2>
          <p class="card-text text-muted mb-3">No tienes permiso para acceder a este recurso.</p>
          <p class="card-text text-muted mb-4">Por favor, contacta a tu administrador si crees que esto es un error.</p>
          <a routerLink="/" class="btn btn-primary btn-lg">
            <i class="bi bi-house-door me-2"></i>
            Regresar al inicio
          </a>
        </div>
      </div>
    </div>
`,
  styles: ''
})
export class Forbidden {

}
