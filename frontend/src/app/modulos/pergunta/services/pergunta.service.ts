import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Pergunta } from 'src/app/dominios/pergunta';
import { environment } from 'src/environments/environment';

@Injectable()
export class PerguntaService{
  
  url = `${environment.apiUrl}/pergunta`;

  constructor( private http: HttpClient) { }

  getPergunta(): Observable<Pergunta[]>{
    return this.http.get<Pergunta[]>(`${this.url}`);
  }

  criarPergunta(pergunta: Pergunta): Observable<Pergunta>{
    return this.http.post<Pergunta>(this.url, pergunta);
  }
}

