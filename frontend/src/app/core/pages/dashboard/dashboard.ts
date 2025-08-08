import { Component, inject } from '@angular/core';
import { AuthClient } from '../../services/auth-client';
import { Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-dashboard',
  imports: [CommonModule, RouterModule],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css'
})
export class Dashboard {
  public readonly authService = inject(AuthClient);
  private readonly router = inject(Router);

  logout(): void {
    this.authService.logout();
  }
}
