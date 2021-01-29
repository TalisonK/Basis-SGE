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

  formUsuario: FormGroup;
  usuario = new Usuario();

  constructor( private fb: FormBuilder, private usuarioService: UsuarioService){}
  
  //  private route: ActivatedRoute

  ngOnInit(): void {
    /*
    this.route.paramMap.subscribe(params => {
      console.log(params);
    })
    */
    this.formUsuario = this.fb.group({
      nome: ['', Validators.minLength(3)],
      cpf: ['', Validators.maxLength(11)],
      email: ['', Validators.email],
      telefone: ['', Validators.maxLength(14)],
      dataNascimento: '',
    });
  }

  criar(){
   
    if(this.formUsuario.invalid){
      alert('Formulário inválido');
      return;
    }
   
    this.usuarioService.criarUsuario(this.usuario).subscribe(usuario => {
      console.log('usuario salvo', usuario);
      alert('Usuário salvo')
    }, (erro: HttpErrorResponse) => {
      alert(erro.message);
    });
  }

  
}
