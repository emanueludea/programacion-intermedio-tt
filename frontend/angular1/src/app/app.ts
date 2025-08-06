import { Component, inject, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Alumnos } from "./domains/alumnos/pages/alumnos/alumnos";

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, Alumnos],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('angular1');
}
