import { Component, OnInit } from '@angular/core';
import { PreInscricao } from 'src/app/dominios/PreInscricao';
import { InscricaoService } from '../../services/inscricao-service.service';

@Component({
  selector: 'app-listagem-inscricao',
  templateUrl: './listagem-inscricao.component.html',
  styleUrls: ['./listagem-inscricao.component.css']
})
export class ListagemInscricaoComponent implements OnInit {

  inscricoes: PreInscricao[] = [];



  constructor(private service:InscricaoService) { }

  ngOnInit(): void {
    this.buscarUsuarioInscricoes();
  }


  buscarUsuarioInscricoes() {
    this.service.getInscricaoUsuario().subscribe((inscricoes: PreInscricao[]) =>{
		  this.inscricoes = inscricoes;
		  console.log(this.inscricoes);
    });
  }
}
