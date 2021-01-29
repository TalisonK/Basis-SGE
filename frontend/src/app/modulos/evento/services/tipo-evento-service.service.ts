import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import {TipoEvento} from 'src/app/dominios/tipo-evento'
import { Observable } from 'rxjs';

@Injectable()
export class TipoEventoService {

  url = `${environment.apiUrl}/tipo-evento`;

  constructor( private http: HttpClient) { }

  getTiposEventos(): Observable<TipoEvento[]>{

    return this.http.get<TipoEvento[]>(`${this.url}`);
  }
}
