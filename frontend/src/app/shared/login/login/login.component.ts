import { EventEmitter, Input } from '@angular/core';
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

  @Output() loginDadosEvent = new EventEmitter();

  cpf:String = "";

  chave:String = "";
  
  constructor() { }

  ngOnInit(): void {
  }

  loginFocusOut(){

    let pacote = {
      cpf:this.cpf,
      chave:this.chave
    }

    this.loginDadosEvent.emit(pacote);
  }
}
