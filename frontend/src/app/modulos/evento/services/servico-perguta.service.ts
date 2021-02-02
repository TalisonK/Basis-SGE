import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ServicoPergutaService {

  url = `${environment.apiUrl}/pergunta`;

  constructor(private http:HttpClient) { }

  buscarPergunta(id:number):Observable<any> {
    return this.http.get(`${this.url}/${id}`);
  }
}
