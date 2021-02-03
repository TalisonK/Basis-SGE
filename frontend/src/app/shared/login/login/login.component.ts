import { EventEmitter } from '@angular/core';
import { Component, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

import { Usuario } from 'src/app/dominios/usuario';
import { UsuarioAutenticacaoDTO } from '../dto/usuarioAutenticacaoDTO';
import { LoginService } from '../login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  @Output() emitUsuario: EventEmitter<Usuario> = new EventEmitter();
  formLogin: FormGroup;
  login = new UsuarioAutenticacaoDTO();


  constructor(
    private servico: LoginService, 
    private formBuilder: FormBuilder 
  ) { }

  ngOnInit(): void {
    this.formLogin = this.formBuilder.group({
      cpf: '',
      chave: ''
    })
  }
  getUserFromLocalStorage(){
    const usuario = JSON.parse(window.localStorage.getItem("usuario"));
    this.emitUsuario.emit(usuario);
  } 
  makeLogin(){
    this.servico.findUserByCpfAndChave(this.login).subscribe((usuario: Usuario) =>{
      this.emitUsuario.emit(usuario);
      
      localStorage.setItem("usuario",JSON.stringify(usuario));
      const usuarioS = JSON.parse(window.localStorage.getItem("usuario"));
      console.log(usuarioS)
    })
  }

  makeLogout(){
    localStorage.clear()
    location.reload()
  }
}
