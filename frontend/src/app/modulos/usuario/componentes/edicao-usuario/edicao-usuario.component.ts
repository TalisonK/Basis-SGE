import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ConfirmationService } from 'primeng';
import { Usuario } from 'src/app/dominios/usuario';
import { UsuarioService } from '../../services/usuario.service';

@Component({
  selector: 'app-edicao-usuario',
  templateUrl: './edicao-usuario.component.html',
  styleUrls: ['./edicao-usuario.component.css']
})
export class EdicaoUsuarioComponent implements OnInit {

  usuario = new Usuario();
  formularioEdicao: boolean;
  formUsuario: FormGroup;
  

  constructor(private servico: UsuarioService, 
    private confirmatioService: ConfirmationService) { }

  ngOnInit(): void {
    this.usuario = JSON.parse(localStorage.getItem("usuario"));
  }
  
  editar(){
    this.servico.editarUsuario(this.usuario).subscribe(usuario => {
      this.usuario = usuario;
    })
  }
}
