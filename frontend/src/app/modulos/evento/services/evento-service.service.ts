import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Evento} from 'src/app/dominios/evento';
import { environment } from 'src/environments/environment';
import { EventoListagem } from './dto/evento-listagem';
import { Pergunta } from 'src/app/dominios/pergunta';


@Injectable()
export class EventoService{

  url = `${environment.apiUrl}/eventos`;

  constructor( private http: HttpClient) { }

  obterPerguntasPorIdEvento(id: number): Observable<Pergunta[]>{
    return this.http.get<Pergunta[]>(`${this.url}/${id}/perguntas`)
  }

  obterEventoPorId(id: number): Observable<Evento> {
    return this.http.get<Evento>(`${this.url}/${id}`);
  }

  getEventos(): Observable<EventoListagem[]>{

    return this.http.get<EventoListagem[]>(`${this.url}`);
  }

  salvarEvento(evento: Evento): Observable<Evento> {
    return this.http.post<Evento>(this.url, evento);
  }

  editarEvento(evento: Evento): Observable<Evento> {
    return this.http.put<Evento>(this.url, evento);
  }

  deletarEvento(id: number): Observable<any> {
    return this.http.delete(`${this.url}/${id}`);
  }
}

