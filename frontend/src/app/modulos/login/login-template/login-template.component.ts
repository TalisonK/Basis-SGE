import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { MessageService } from 'primeng/api';
import { Usuario } from 'src/app/dominios/usuario';
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
    private servico: LoginServico,
    private messageService:MessageService
    
  ) { }

  ngOnInit(): void {
    if(localStorage.getItem("usuario")){this.UsuarioEvent.emit(null);}
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

  makeLogin(){
    if(this.validaDadosLogin()){return}
    this.servico.findUserByCpfAndChave(this.login).subscribe((usuario: Usuario) =>{

      if(!localStorage.getItem("usuario"))localStorage.setItem("usuario",JSON.stringify(usuario));
      this.addSingle("success", "login efetuado com sucesso!","")
      this.UsuarioEvent.emit(usuario);      
    }, err => {
      this.addSingle("error", "Dados inválidos", "")
    })
  }

  enviarCadastro(){
    if(this.validaDadoCadastro()) return;

    this.usuario.cpf = this.usuario.cpf.replace(".", "").replace(".", "").replace("-", "");
    this.usuario.telefone = this.usuario.telefone.replace("(", "").replace(")", "").replace(" ", "").replace("-", "");

    this.servico.criarUsuario(this.usuario).subscribe((usuario) => {
      this.cadastroEventoff();
      this.addSingle("success", "Cadastro concluido", "Cadastrado com sucesso");
    }, 
    err => {
      if(err.error.errors){
        err.error.errors.forEach(element => {
          this.addSingle("error", element.message, "")
        });
      }
      else{
        this.addSingle("error", err.error.message, "")
      }
    })
  }

  validaDadosLogin(){
    if(this.login.cpf == ""){
      this.addSingle("error", "Insira um CPF válido","");
      return true;
    }
    if(this.login.chave == ""){
      this.addSingle("error", "Insira uma chave válida","");
      return true;
    }
  }

  validaDadoCadastro(){
    if(!this.usuario.nome){
      this.addSingle("error", "Insira um nome", "");
      return true;
    }

    if(!this.usuario.cpf){
      this.addSingle("error", "Insira um cpf válido", "");
      return true;
    }

    if(!this.usuario.email){
      this.addSingle("error", "Insira um email válido", "");
      return true;
    }

    if(!this.usuario.telefone){
      this.addSingle("error", "Insira um telefone válido", "");
      return true;
    }

    if(!this.usuario.dataNascimento){
      this.addSingle("error", "Insira uma data de nascimento válida", "");
      return true;
    }
  }

  addSingle(error, summary, detail) {
    this.messageService.add({severity:error, summary:summary, detail:detail});
  }
}