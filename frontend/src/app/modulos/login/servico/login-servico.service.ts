import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Usuario } from 'src/app/dominios/usuario';
import { environment } from 'src/environments/environment';
import { UsuarioAutenticacaoDTO } from '../dto/usuarioAutenticacaoDTO';

@Injectable({
  providedIn: 'root'
})
export class LoginServico {

  url = `${environment.apiUrl}`;

  constructor(private http: HttpClient) { }

  findUserByCpfAndChave(login:UsuarioAutenticacaoDTO): Observable<Usuario>{
    return this.http.post<Usuario>(`${this.url}/login`, login);
  }

  criarUsuario(usuario: Usuario): Observable<Usuario> {
    return this.http.post<Usuario>(`${this.url}/usuarios`, usuario);
  }
}