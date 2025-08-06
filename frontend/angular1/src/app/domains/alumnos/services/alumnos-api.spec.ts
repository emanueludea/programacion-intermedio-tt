import { TestBed } from '@angular/core/testing';

import { AlumnosApi } from './alumnos-api';

describe('AlumnosApi', () => {
  let service: AlumnosApi;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AlumnosApi);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
