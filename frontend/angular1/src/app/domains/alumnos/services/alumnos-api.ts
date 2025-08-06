import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { inject, Injectable, signal } from '@angular/core';
import { Alumno, AlumnoDTO } from '../models/alumno';
import { catchError, Observable, tap, throwError } from 'rxjs';
import { environment } from '../../../../environments/environment.prod';

@Injectable({
  providedIn: 'root'
})
export class AlumnosApi {

  private readonly http = inject(HttpClient);
  private readonly apiUrl = `${environment.apiUrl}/alumnos`;
  
  // Signal for reactive state management
  private readonly alumnosSignal = signal<Alumno[]>([]);
  private readonly isLoadingSignal = signal<boolean>(false);
  
  // Readonly signals for external consumption
  public readonly alumnos = this.alumnosSignal.asReadonly();
  public readonly isLoading = this.isLoadingSignal.asReadonly();

  getAllAlumnos(): Observable<Alumno[]> {
    this.isLoadingSignal.set(true);
    
    return this.http.get<Alumno[]>(this.apiUrl)
      .pipe(
        tap(alumnos => {
          this.alumnosSignal.set(alumnos);
          this.isLoadingSignal.set(false);
        }),
        catchError(error => {
          this.isLoadingSignal.set(false);
          return this.handleError(error);
        })
      );
  }

  getAlumnoByCedula(cedula: number): Observable<Alumno> {
    return this.http.get<Alumno>(`${this.apiUrl}/${cedula}`)
      .pipe(
        catchError(error => this.handleError(error))
      );
  }

  createAlumno(alumno: AlumnoDTO): Observable<Alumno> {
    this.isLoadingSignal.set(true);
    
    return this.http.post<Alumno>(this.apiUrl, alumno)
      .pipe(
        tap(newAlumno => {
          const currentAlumnos = this.alumnosSignal();
          this.alumnosSignal.set([...currentAlumnos, newAlumno]);
          this.isLoadingSignal.set(false);
        }),
        catchError(error => {
          this.isLoadingSignal.set(false);
          return this.handleError(error);
        })
      );
  }

  updateAlumno(cedula: number, alumno: Partial<AlumnoDTO>): Observable<Alumno> {
    this.isLoadingSignal.set(true);
    
    return this.http.put<Alumno>(`${this.apiUrl}/${cedula}`, alumno)
      .pipe(
        tap(updatedAlumno => {
          const currentAlumnos = this.alumnosSignal();
          const index = currentAlumnos.findIndex((a: { cedula: number; }) => a.cedula === cedula);
          if (index !== -1) {
            const newAlumnos = [...currentAlumnos];
            newAlumnos[index] = updatedAlumno;
            this.alumnosSignal.set(newAlumnos);
          }
          this.isLoadingSignal.set(false);
        }),
        catchError(error => {
          this.isLoadingSignal.set(false);
          return this.handleError(error);
        })
      );
  }

  deleteAlumno(cedula: number): Observable<void> {
    this.isLoadingSignal.set(true);
    
    return this.http.delete<void>(`${this.apiUrl}/${cedula}`)
      .pipe(
        tap(() => {
          const currentAlumnos = this.alumnosSignal();
          const filteredAlumnos = currentAlumnos.filter((a: { cedula: number; }) => a.cedula !== cedula);
          this.alumnosSignal.set(filteredAlumnos);
          this.isLoadingSignal.set(false);
        }),
        catchError(error => {
          this.isLoadingSignal.set(false);
          return this.handleError(error);
        })
      );
  }

  getAlumnoMaterias(cedula: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/${cedula}/materias`)
      .pipe(
        catchError(error => this.handleError(error))
      );
  }

  assignMateriaToAlumno(cedula: number, materiaId: number): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/${cedula}/materias/${materiaId}`, {})
      .pipe(
        catchError(error => this.handleError(error))
      );
  }

  private handleError(error: HttpErrorResponse): Observable<never> {
    let errorMessage = 'An unknown error occurred';
    
    if (error.error instanceof ErrorEvent) {
      // Client-side error
      errorMessage = `Error: ${error.error.message}`;
    } else {
      // Server-side error
      switch (error.status) {
        case 400:
          errorMessage = 'Bad request. Please check your input.';
          break;
        case 401:
          errorMessage = 'Unauthorized. Please log in.';
          break;
        case 403:
          errorMessage = 'Forbidden. Admin access required for alumni operations.';
          break;
        case 404:
          errorMessage = 'Alumno not found.';
          break;
        case 409:
          errorMessage = 'Alumno already exists.';
          break;
        case 500:
          errorMessage = 'Internal server error. Please try again later.';
          break;
        default:
          errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
      }
    }
    
    console.error('AlumnoService Error:', error);
    return throwError(() => new Error(errorMessage));
  }
}
