import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PreInscricao } from 'src/app/dominio/PreInscricao';
import { environment } from 'src/environments/environment';

@Injectable()
export class InscricaoService {

  url =   `${environment.apiUrl}/inscricao`;


  constructor(private http: HttpClient) { }

  getInscricao(): Observable<PreInscricao[]>{

    return this.http.get<PreInscricao[]>(`${this.url}`);
  }
}
