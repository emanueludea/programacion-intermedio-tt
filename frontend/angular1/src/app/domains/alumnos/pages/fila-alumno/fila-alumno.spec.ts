import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FilaAlumno } from './fila-alumno';

describe('FilaAlumno', () => {
  let component: FilaAlumno;
  let fixture: ComponentFixture<FilaAlumno>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FilaAlumno]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FilaAlumno);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
