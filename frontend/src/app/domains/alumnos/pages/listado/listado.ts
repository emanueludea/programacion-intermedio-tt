import { Component, input, output, PipeTransform } from '@angular/core';
import { Alumno } from '../../models/alumno';
import { map, Observable, startWith } from 'rxjs';
import { FormControl, ReactiveFormsModule } from '@angular/forms';
import { AsyncPipe, DecimalPipe } from '@angular/common';
import { NgbHighlight } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-listado-alumnos',
  imports: [DecimalPipe, AsyncPipe, ReactiveFormsModule, NgbHighlight],
  templateUrl: './listado.html',
  providers: [DecimalPipe],
})
export class Listado {
  alumnos = input<Alumno[]>([]);
  delete = output<Alumno>();
  update = output<Alumno>();
  alumnos$: Observable<Alumno[]>;
  filter = new FormControl('', { nonNullable: true });

  constructor(pipe: DecimalPipe) {
    this.alumnos$ = this.filter.valueChanges.pipe(
      startWith(''),
      map((text) => this.search(text, pipe)),
    );
  }
  search(text: string, pipe: PipeTransform): Alumno[] {
    return this.alumnos().filter((alumno) => {
      const term = text.toLowerCase();
      return (
        alumno.primerNombre.toLowerCase().includes(term) ||
        alumno.segundoNombre?.toLowerCase().includes(term) ||
        alumno.primerApellido.toLowerCase().includes(term) ||
        alumno.segundoApellido?.toLowerCase().includes(term) ||
        pipe.transform(alumno.edad).includes(term) ||
        pipe.transform(alumno.semestre).includes(term)
      );
    });
  }

  editarAlumno(alumno: Alumno) {
    console.log("Editar alumno: " + alumno.cedula);
    this.update.emit(alumno);
  }
  eliminarAlumno(alumno: Alumno) {
    console.log("Eliminar alumno: " + alumno.cedula);
    this.delete.emit(alumno);
  }
}
