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

  buscarPerguntaPorId(id: number): Observable<Pergunta> {
    return this.http.get<Pergunta>(`${this.url}/${id}`);
  }
  
  editarPergunta(pergunta: Pergunta): Observable<Pergunta>{
    return this.http.put<Pergunta>(this.url, pergunta);
  }

  deletarPergunta(id: number): Observable<any>{
    return this.http.delete(`${this.url}/${id}`);
  }  

}

