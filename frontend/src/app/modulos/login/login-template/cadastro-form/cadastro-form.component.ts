import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Usuario } from 'src/app/dominios/usuario';

@Component({
  selector: 'app-cadastro-form',
  templateUrl: './cadastro-form.component.html',
  styleUrls: ['./cadastro-form.component.css']
})
export class CadastroFormComponent implements OnInit {

  @Output() cadastroEvento = new EventEmitter();

  usuario:Usuario = new Usuario();

  constructor() { }

  ngOnInit(): void {
  }

  loginFocusOut(){
    this.cadastroEvento.emit(this.usuario);
  }

}
