import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Modulo } from '../Modulo';

@Injectable({
  providedIn: 'root',
})
export class ModuloService {
  private apiUrl = 'http://localhost:8080/v1/discretas-service/modulos';

  constructor(private http: HttpClient) {}

  getModulos(): Observable<Modulo[]> {
    return this.http.get<Modulo[]>(`${this.apiUrl}/todos`);
  }

  getModulo(id: number): Observable<Modulo> {
    return this.http.get<Modulo>(`${this.apiUrl}/${id}`);
  }

  createModulo(modulo: Modulo): Observable<Modulo> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post<Modulo>(this.apiUrl, modulo, { headers });
  }

  updateModulo(modulo: Modulo): Observable<Modulo> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.put<Modulo>(`${this.apiUrl}/${modulo.id}`, modulo, { headers });
  }

  deleteModulo(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
