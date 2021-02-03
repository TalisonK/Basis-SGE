import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Usuario } from 'src/app/dominios/usuario';
import { UsuarioAutenticacaoDTO } from '../dto/usuarioAutenticacaoDTO';
import { LoginService } from '../login.service';

@Component({
  selector: 'app-login-template',
  templateUrl: './login-template.component.html',
  styleUrls: ['./login-template.component.css']
})
export class LoginTemplateComponent implements OnInit {

  @Output() emitUsuarioEvent = new EventEmitter();

  @Input() titulo:String = "Faça seu Login";

  login = new UsuarioAutenticacaoDTO();

  constructor(
    private servico: LoginService
  ) { }

  ngOnInit(): void {
  }

  receberLoginDados(pacote){
    this.login.chave = pacote.chave;
    this.login.cpf = pacote.cpf;

    this.login.cpf = this.login.cpf.replace(".","");
    this.login.cpf = this.login.cpf.replace(".","");
    this.login.cpf = this.login.cpf.replace("-","");
  }

  getUserFromLocalStorage(){
    const usuario = JSON.parse(window.localStorage.getItem("usuario"));
    this.emitUsuarioEvent.emit(usuario);
  } 
  makeLogin(){

    this.servico.findUserByCpfAndChave(this.login).subscribe((usuario: Usuario) =>{
      this.emitUsuarioEvent.emit(usuario);
      
      localStorage.setItem("usuario",JSON.stringify(usuario));
      console.log(localStorage.getItem("usuario"));
    })
  }

  makeLogout(){
    localStorage.clear()
    location.reload()
  }


}
