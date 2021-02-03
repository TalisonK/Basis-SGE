import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Usuario } from 'src/app/dominios/usuario';
import { environment } from 'src/environments/environment';
import { UsuarioAutenticacaoDTO } from './dto/usuarioAutenticacaoDTO';

@Injectable()
export class LoginService {

  url = `${environment.apiUrl}/login`;

  constructor( private http: HttpClient) { }

  findUserByCpfAndChave(usuarioAutenticacaoDTO: UsuarioAutenticacaoDTO): Observable<Usuario> {
    return this.http.post<Usuario>(this.url, usuarioAutenticacaoDTO);
  }


}
