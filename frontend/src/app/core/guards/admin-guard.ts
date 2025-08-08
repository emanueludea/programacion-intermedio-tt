import { CanActivateFn, Router } from '@angular/router';
import { AuthClient } from '../services/auth-client';
import { inject } from '@angular/core';

export const adminGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthClient);
  const router = inject(Router);

  if (authService.isAuthenticated() && authService.isAdmin()) {
    return true;
  }

  if (authService.isAuthenticated()) {
    // User is authenticated but not admin
    router.navigate(['/forbidden']);
    return false;
  }

  // User is not authenticated
  router.navigate(['/login'], { queryParams: { returnUrl: state.url } });
  return false;
};
