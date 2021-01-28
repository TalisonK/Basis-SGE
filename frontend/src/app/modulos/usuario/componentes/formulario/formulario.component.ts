import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
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

  constructor( private fb: FormBuilder, private usuarioService: UsuarioService) { }

  ngOnInit(): void {

    this.formUsuario = this.fb.group({
      nome: ['', Validators.minLength(3)],
      cpf: '',
      email: '',
      telefone: '',
      dataNascimento: '',
    })
  }

  criar(){
    if(this.formUsuario.invalid){
      alert('Inválido');
      return;
    }
    this.usuarioService.criarUsuario(this.usuario).subscribe(usuario => {
      console.log('salvou', usuario);
      alert('Usuário salvo')
    }, (erro: HttpErrorResponse) => {
      alert(erro.message);
    })
  }
}
