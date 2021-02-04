import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Usuario } from 'src/app/dominios/usuario';
import { UsuarioService } from '../../usuario/services/usuario.service';
import { UsuarioAutenticacaoDTO } from '../dto/usuarioAutenticacaoDTO';
import { LoginServico } from '../servico/login-servico.service';

@Component({
  selector: 'app-login-template',
  templateUrl: './login-template.component.html',
  styleUrls: ['./login-template.component.css']
})
export class LoginTemplateComponent implements OnInit {

  @Output() UsuarioEvent = new EventEmitter();

  @Input() titulo:String = "Faça seu Login";

  login = new UsuarioAutenticacaoDTO();

  usuario = new Usuario();

  @Input() cadastro = false;

  constructor(
    private servico: LoginServico
    
  ) { }

  ngOnInit(): void {
  }

  makeLogout(){
    localStorage.clear()
    location.reload()
  }

  cadastroEventon(){
    this.cadastro = true;
  }

  cadastroEventoff(){
    this.cadastro = false;
  }

  cadastrarUsuario(usuario){
    this.usuario = usuario;
  }

  receberLoginDados(pacote){
    this.login.chave = pacote.chave;
    this.login.cpf = pacote.cpf;

    this.login.cpf = this.login.cpf.replace(".", "").replace(".", "").replace("-", "");
  }

  getUserFromLocalStorage(){
    const usuario = JSON.parse(window.localStorage.getItem("usuario"));
    this.UsuarioEvent.emit(usuario);
  } 
  makeLogin(){

    this.servico.findUserByCpfAndChave(this.login).subscribe((usuario: Usuario) =>{
      this.UsuarioEvent.emit(usuario);
      
      localStorage.setItem("usuario",JSON.stringify(usuario));
      console.log(localStorage.getItem("usuario"));
      
    })
  }

  enviarCadastro(){

    this.usuario.cpf = this.usuario.cpf.replace(".", "").replace(".", "").replace("-", "");

    this.usuario.telefone = this.usuario.telefone.replace("(", "").replace(")", "").replace(" ", "").replace("-", "");

    this.servico.criarUsuario(this.usuario).subscribe((usuario) => {
      console.log(usuario);
    })
  }
}