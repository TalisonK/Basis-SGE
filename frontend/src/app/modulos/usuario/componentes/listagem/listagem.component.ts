import { Component, OnInit } from '@angular/core';
import { Usuario } from 'src/app/dominios/usuario';
import { UsuarioService } from '../../services/usuario.service';

@Component({
  selector: 'app-listagem',
  templateUrl: './listagem.component.html',
  styleUrls: ['./listagem.component.css']
  
})
export class ListagemComponent implements OnInit {


  usuarios: Usuario[] = [];


  
  constructor( private servico: UsuarioService) { }


  ngOnInit(): void {
    this.buscarUsuarios();
  }

  private buscarUsuarios(){
    this.servico.getUsuarios()
    .subscribe((usuarios: Usuario[]) => {
    this.usuarios = usuarios;
    });
  }
} 
