import { Component, OnInit } from '@angular/core';
import { Pergunta } from 'src/app/dominios/pergunta';
import { PerguntaService } from '../../services/pergunta.service';

@Component({
  selector: 'app-listagem',
  templateUrl: './listagem.component.html',
  styleUrls: ['./listagem.component.css']
})
export class ListagemComponent implements OnInit {

  perguntas: Pergunta[] = [];

  constructor( private servico: PerguntaService) { }

  ngOnInit(): void {
    this.buscarPerguntas();
  }

  private buscarPerguntas(){
    this.servico.getPerguntas()
    .subscribe((pergunta: Pergunta[]) => {
    this.perguntas = pergunta;
    });
  }
}
