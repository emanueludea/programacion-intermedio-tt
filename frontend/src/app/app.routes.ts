import { Routes } from '@angular/router';
import { Alumnos } from './domains/alumnos/pages/alumnos/alumnos';
import { Materias } from './domains/materias/pages/materias/materias';
import { Matriculas } from './domains/matriculas/matriculas';

export const routes: Routes = [
    { path: '', component: Alumnos, title: 'Alumnos' },
    { path: 'materias', component: Materias, title: 'Alumnos' },
    { path: 'matriculas', component: Matriculas, title: 'Alumnos' },
];
