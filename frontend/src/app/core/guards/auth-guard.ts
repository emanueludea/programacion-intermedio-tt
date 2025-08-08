import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthClient } from '../services/auth-client';

export const authGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthClient);
  const router = inject(Router);

  if (authService.isAuthenticated()) {
    return true;
  }

  // Redirect to login with return url
  router.navigate(['/login'], { queryParams: { returnUrl: state.url } });
  return false;
};
