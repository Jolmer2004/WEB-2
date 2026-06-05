import { afterEach, beforeEach, describe, expect, it } from 'vitest';
import { TestBed } from '@angular/core/testing';
import { provideHttpClient } from '@angular/common/http';
import { HttpTestingController, provideHttpClientTesting, } from '@angular/common/http/testing';
import { Modulo } from '../Modulo';
import { ModuloService } from './modulo.service';

describe('ModuloService', () => {
  let service: ModuloService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    service = TestBed.inject(ModuloService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should get all modulos', () => {
    const mockModulos: Modulo[] = [
      { id: 1, titulo: 'Modulo 1', descripcion: 'Descripcion 1', orden: 1 },
    ];

    service.getModulos().subscribe((modulos) => {
      expect(modulos).toEqual(mockModulos);
    });

    const request = httpMock.expectOne(
      'http://localhost:8080/v1/discretas-service/modulos/todos',
    );
    expect(request.request.method).toBe('GET');
    request.flush(mockModulos);
  });
});
