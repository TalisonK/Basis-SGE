import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Usuario } from 'src/app/dominios/usuario';
import { environment } from 'src/environments/environment';

@Injectable()
export class UsuarioService {

  url = `${environment.apiUrl}/usuarios`;

  constructor( private http: HttpClient) { }

  getUsuarios(): Observable<Usuario[]>{

    return this.http.get<Usuario[]>(`${this.url}`);
  }

  criarUsuario(usuario: Usuario): Observable<Usuario> {
    return this.http.post<Usuario>(this.url, usuario);
  }

  buscarUsuarioPorId(id: number): Observable<Usuario> {
    return this.http.get<Usuario>(`${this.url}/${id}`);
  }

  editarUsuario(usuario: Usuario): Observable<Usuario>{
    return this.http.put<Usuario>(this.url, usuario);
  }

  deletarUsuario(id: number): Observable<any>{
    return this.http.delete(`${this.url}/${id}`);

  }

}
