import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { AuthClient } from '../services/auth-client';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const authReq = req.clone({
    setHeaders: {
      'Content-Type': 'application/json'
    },
    withCredentials: true
  });
  return next(authReq);
};
