import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Usuario } from 'src/app/dominios/usuario';
import { UsuarioService } from '../../services/usuario.service';

@Component({
  selector: 'app-formulario',
  templateUrl: './formulario.component.html',
  styleUrls: ['./formulario.component.css']
})
export class FormularioComponent implements OnInit {

  edicao = false;
  formUsuario: FormGroup;
  usuario = new Usuario();
  

  constructor( 
    private fb: FormBuilder, 
    private usuarioService: UsuarioService,
    private route: ActivatedRoute
    ){}

  ngOnInit(): void {
    
    this.route.params.subscribe(params => {
      if (params.id){
        this.edicao = true;
        this.buscarUsuario(params.id);
      }
    });
    this.formUsuario = this.fb.group({
      nome: ['', Validators.minLength(3)],
      cpf: ['', Validators.maxLength(11)],
      email: ['', Validators.email],
      telefone: ['', Validators.maxLength(14)],
      dataNascimento: '',
    });
  }

  buscarUsuario(id: number){
    this.usuarioService.buscarUsuarioPorId(id)
    .subscribe(usuario => this.usuario = usuario);
  }
  criar(){
   
    if(this.formUsuario.invalid){
      alert('Formulário inválido');
      return;
    }
   
    if (this.edicao) {
      this.usuarioService.editarUsuario(this.usuario)
        .subscribe(usuario => {
          alert('Usuário Editado')
        }, (erro: HttpErrorResponse) => {
          alert(erro.error.message);
        });
    } else {
      this.usuarioService.criarUsuario(this.usuario)
        .subscribe(usuario => {
          alert('Usuário Salvo')
        }, (erro: HttpErrorResponse) => {
          alert(erro.error.message);
        });
      }
    }
  }
