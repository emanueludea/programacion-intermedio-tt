import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Alumno } from './alumno';
import { catchError, Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MyService {
  private http = inject(HttpClient);
  private readonly apiUrl = `http://localhost:8080/api/alumnos`;
 
  getAllAlumnos(): Observable<Alumno[]> {
    return this.http.get<Alumno[]>(this.apiUrl).pipe(
      tap(alumnos => console.log('Fetched alumnos:', alumnos)),
      catchError(() => {
        console.log('Hubo error al obtener los alumnos');
        return [];
      })
    );
  }

  createAlumno(){

  }

  updateAlumno(){

  }

  deleteAlumno(){

  }
}
