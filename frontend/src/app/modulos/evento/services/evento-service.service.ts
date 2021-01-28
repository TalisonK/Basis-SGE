import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Evento} from 'src/app/dominios/evento';
import { environment } from 'src/environments/environment';


@Injectable()
export class EventoService{

  url = `${environment.apiUrl}/eventos`;

  constructor( private http: HttpClient) { }

  getEventos(): Observable<Evento[]>{

    return this.http.get<Evento[]>(`${this.url}`);
  }
}

