import { EventEmitter, Input } from '@angular/core';
import { Component, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
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