import { Component, OnInit } from '@angular/core';
import { InscricaoListagem } from 'src/app/dominios/InscricaoListagem';
import { InscricaoService } from '../../services/inscricao-service.service';

@Component({
  selector: 'app-listagem-inscricao',
  templateUrl: './listagem-inscricao.component.html',
  styleUrls: ['./listagem-inscricao.component.css']
})
export class ListagemInscricaoComponent implements OnInit {

  private inscricoes: InscricaoListagem[] = [];


  constructor(private service:InscricaoService) { }

  ngOnInit(): void {
    this.buscarUsuarioInscricoes();
  }


  buscarUsuarioInscricoes() {
    this.service.getInscricao().subscribe((inscricoes: InscricaoListagem[]) =>{
		  this.inscricoes = inscricoes;
		  console.log(this.inscricoes);
    });
  }
}
