import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Evento} from 'src/app/dominios/evento';
import { environment } from 'src/environments/environment';
import { EventoListagem } from './dto/evento-listagem';


@Injectable()
export class EventoService{

  url = `${environment.apiUrl}/eventos`;

  constructor( private http: HttpClient) { }

  getEventos(): Observable<EventoListagem[]>{

    return this.http.get<EventoListagem[]>(`${this.url}`);
  }
}

