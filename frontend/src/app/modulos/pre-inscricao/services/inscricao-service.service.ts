import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { InscricaoListagem } from 'src/app/dominios/InscricaoListagem';
import { InscricaoResposta } from 'src/app/dominios/InscricaoResposta';
import { PreInscricao } from 'src/app/dominios/PreInscricao';
import { environment } from 'src/environments/environment';
import { PerguntaResposta } from '../dto/Conjunto';

@Injectable()
export class InscricaoService {

  url = `${environment.apiUrl}`;

  constructor(private http: HttpClient) { }

  getInscricao(): Observable<InscricaoListagem[]>{
    return this.http.get<InscricaoListagem[]>(`${this.url}/inscricao`);
  }

  getInscricaoUsuario(): Observable<PreInscricao[]>{
    return this.http.get<PreInscricao[]>(`${this.url}/inscricao/usuario`);
  }

  getInscricaoPorIdUsuario(id: number): Observable<InscricaoListagem[]>{
    return this.http.get<InscricaoListagem[]>(`${this.url}/inscricao/usuario/${id}`);
  }

  getInscricaoPorId(id: number): Observable<PreInscricao>{
    return this.http.get<PreInscricao>(`${this.url}/inscricao/${id}`);
  }

  enviarInscricao(inscricao:PreInscricao): Observable<PreInscricao>{
    return this.http.put<PreInscricao>(`${this.url}/inscricao`,inscricao);
  }

  enviarResposta(resposta:InscricaoResposta): Observable<InscricaoResposta>{
    return this.http.post<InscricaoResposta>(`${this.url}/inscricaoResposta`,resposta);
  }

  editarInscricao(inscricao: PreInscricao): Observable<InscricaoListagem>{
    return this.http.put<InscricaoListagem>(`${this.url}/inscricao`, inscricao);
  }

  getRespostas(inscricao: PreInscricao): Observable<PerguntaResposta[]>{
    return this.http.post<PerguntaResposta[]>(`${this.url}/inscricao/respostas`, inscricao);
  }
}
