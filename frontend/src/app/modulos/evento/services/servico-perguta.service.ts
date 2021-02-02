import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { InscricaoResposta } from 'src/app/dominios/InscricaoResposta';
import { PreInscricao } from 'src/app/dominios/PreInscricao';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ServicoPergutaService {

  url = `${environment.apiUrl}`;

  constructor(private http:HttpClient) { }

  buscarPergunta(id:number):Observable<any> {
    return this.http.get(`${this.url}/pergunta/${id}`);
  }

  salvarResposta(resposta:InscricaoResposta): Observable<any> {
    return this.http.post(`${this.url}/inscricaoResposta`, resposta);
  }

    criarInscricao(inscricao:PreInscricao): Observable<any> {
    return this.http.post(`${this.url}/inscricao`, inscricao);
  }
}
