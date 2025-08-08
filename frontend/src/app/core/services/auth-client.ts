import { HttpClient } from '@angular/common/http';
import { computed, inject, Injectable, signal } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from '../../../environments/environment';
import { AuthResponse, AuthStatusResponse, LoginRequest, User } from '../models/user';
import { catchError, map, Observable, tap, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthClient {
  private readonly http = inject(HttpClient);
  private readonly router = inject(Router);
  private readonly apiUrl = `${environment.apiUrl}/users`;
  private readonly tokenKey = 'jwt_token';

  private readonly currentUserSignal = signal<User | null>(null);
  private readonly isLoadingSignal = signal<boolean>(false);
  private readonly isAuthenticatedSignal = signal<boolean>(false);


  public readonly currentUser = this.currentUserSignal.asReadonly();
  public readonly isAuthenticated = computed(() => !!this.currentUserSignal());
  public readonly isAdmin = computed(() => this.currentUserSignal()?.isAdmin ?? false);
  public readonly isLoading = this.isLoadingSignal.asReadonly();

  constructor() {
    this.initializeAuth();
  }

  private initializeAuth(): void {
    // Check authentication status with the server
    this.checkAuthStatus().subscribe({
      next: (isAuthenticated) => {
        if (!isAuthenticated) {
          this.currentUserSignal.set(null);
          this.isAuthenticatedSignal.set(false);
        }
      },
      error: () => {
        this.currentUserSignal.set(null);
        this.isAuthenticatedSignal.set(false);
      }
    });
  }

  private checkAuthStatus(): Observable<boolean> {
    return this.http.get<AuthStatusResponse>(`${this.apiUrl}/auth-status`, { withCredentials: true })
      .pipe(
        tap(response => {
          this.isAuthenticatedSignal.set(response.authenticated);
          if (response.authenticated && response.user) {
            this.currentUserSignal.set(response.user);
          }
        }),
        map(response => response.authenticated),
        catchError(() => {
          this.isAuthenticatedSignal.set(false);
          return throwError(() => new Error('Auth check failed'));
        })
      );
  }

  login(credentials: LoginRequest): Observable<AuthResponse> {
    this.isLoadingSignal.set(true);

    return this.http.post<AuthResponse>(`${this.apiUrl}/authenticate`, credentials)
      .pipe(
        tap(response => {
          this.currentUserSignal.set({
            username: response.username,
            isAdmin: response.isAdmin
          });
          this.isAuthenticatedSignal.set(true);
        }),
        catchError(error => {
          this.isLoadingSignal.set(false);
          return throwError(() => error);
        }),
        tap(() => this.isLoadingSignal.set(false))
      );
  }

  register(user: User): Observable<User> {
    this.isLoadingSignal.set(true);

    return this.http.post<User>(this.apiUrl, user, { withCredentials: true })
      .pipe(
        catchError(error => {
          this.isLoadingSignal.set(false);
          return throwError(() => error);
        }),
        tap(() => this.isLoadingSignal.set(false))
      );
  }

  logout(): void {
    // Call server logout endpoint to clear the HttpOnly cookie
    this.http.post(`${this.apiUrl}/logout`, {}, { withCredentials: true })
      .subscribe({
        next: () => {
          this.currentUserSignal.set(null);
          this.isAuthenticatedSignal.set(false);
          this.router.navigate(['/login']);
        },
        error: () => {
          // Even if logout fails on server, clear local state
          this.currentUserSignal.set(null);
          this.isAuthenticatedSignal.set(false);
          this.router.navigate(['/login']);
        }
      });
  }

  refreshUserData(): Observable<User> {
    const currentUser = this.currentUserSignal();
    if (!currentUser?.username) {
      return throwError(() => new Error('No hay usuario autenticado'));
    }

    return this.http.get<User>(`${this.apiUrl}/${currentUser.username}`, { withCredentials: true })
      .pipe(
        tap(user => this.currentUserSignal.set(user)),
        catchError(error => {
          if (error.status === 401) {
            this.logout();
          }
          return throwError(() => error);
        })
      );
  }
}
