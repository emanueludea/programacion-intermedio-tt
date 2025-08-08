import { Routes } from '@angular/router';
import { Alumnos } from './domains/alumnos/pages/alumnos/alumnos';
import { Materias } from './domains/materias/pages/materias/materias';
import { Matriculas } from './domains/matriculas/matriculas';
import { Login } from './core/pages/login/login';
import { Dashboard } from './core/pages/dashboard/dashboard';
import { adminGuard } from './core/guards/admin-guard';
import { authGuard } from './core/guards/auth-guard';
import { Forbidden } from './core/pages/forbidden/forbidden';

export const routes: Routes = [
    { path: '', component: Dashboard, title: 'Inicio' },
    { path: 'login', component: Login, title: 'Iniciar sesión' },
    { path: 'estudiantes', component: Alumnos, title: 'Estudiantes', canActivate: [adminGuard] },
    { path: 'cursos', component: Materias, title: 'Cursos', canActivate: [authGuard] },
    { path: 'matriculas', component: Matriculas, title: 'Matriculas' },
    { path: 'forbidden', component: Forbidden, title: 'Acceso Denegado' },
    { path: '**', redirectTo: '' },
];
