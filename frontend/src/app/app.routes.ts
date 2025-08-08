import { Routes } from '@angular/router';
import { Alumnos } from './domains/alumnos/pages/alumnos/alumnos';
import { Materias } from './domains/materias/pages/materias/materias';
import { Matriculas } from './domains/matriculas/matriculas';
import { Login } from './core/pages/login/login';
import { Dashboard } from './core/pages/dashboard/dashboard';

export const routes: Routes = [
    { path: '', component: Dashboard, title: 'Inicio' },
    { path: 'login', component: Login, title: 'Iniciar sesión' },
    { path: 'estudiantes', component: Alumnos, title: 'Estudiantes' },
    { path: 'cursos', component: Materias, title: 'Cursos' },
    { path: 'matriculas', component: Matriculas, title: 'Matriculas' },
];
