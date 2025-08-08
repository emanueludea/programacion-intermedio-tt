import { HttpErrorResponse, HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, throwError } from 'rxjs';
import { AuthClient } from '../services/auth-client';

export const errorInterceptor: HttpInterceptorFn = (req, next) => {
  const authService = inject(AuthClient);
  const router = inject(Router);

  return next(req).pipe(
    catchError((error: HttpErrorResponse) => {
      if (error.status === 401) {
        // Auto logout if 401 response returned from api
        authService.logout();
        location.reload();
      }

      if (error.status === 403) {
        router.navigate(['/forbidden']);
      }

      return throwError(() => error);
    })
  );
};
