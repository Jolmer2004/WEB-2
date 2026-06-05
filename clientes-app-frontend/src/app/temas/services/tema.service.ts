import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Tema } from '../Tema';


@Injectable({
  providedIn: 'root',
})
export class TemaService {
  private apiUrl = 'http://localhost:8080/api/v1/temas-service/temas';

  constructor(private http: HttpClient) {}

  // ── Obtener todos los clientes ────────────────────────────────────────────
  getTemas(): Observable<Tema[]> {
    return this.http.get<Tema[]>(this.apiUrl+'/todos');
  }

  // ── Obtener un tema por ID ─────────────────────────────────────────────
  getTema(id: number): Observable<Tema> {
    return this.http.get<Tema>(`${this.apiUrl}/${id}`);
  }

  // ── Crear un nuevo tema ────────────────────────────────────────────────
  createTema(tema: Tema): Observable<Tema> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post<Tema>(this.apiUrl, tema, { headers });
  }

  // ── Actualizar un tema existente ───────────────────────────────────────
  updateTema(tema: Tema): Observable<Tema> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.put<Tema>(`${this.apiUrl}/${tema.id}`, tema, { headers });
  }

  // ── Eliminar un tema ───────────────────────────────────────────────────
  deleteTema(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
