import { Component, OnInit} from '@angular/core';
import { Output, EventEmitter } from '@angular/core';
import { Pergunta } from 'src/app/dominios/pergunta';
import { PerguntaService } from 'src/app/modulos/pergunta/services/pergunta.service';

@Component({
  selector: 'app-lista-pergunta',
  templateUrl: './lista-pergunta.component.html',
  styleUrls: ['./lista-pergunta.component.css']
})
export class ListaPerguntaComponent implements OnInit {

  pergunta: Pergunta[] = [];

  listaPergunta: Pergunta[] = [];
  @Output() listaPerguntaEvent = new EventEmitter<Pergunta[]>();

  constructor(private servico: PerguntaService) { }

  ngOnInit(): void {
    this.buscarPergunta();
  }

  private buscarPergunta(){
    this.servico.getPergunta()
    .subscribe((pergunta: Pergunta[]) => {
    this.pergunta = pergunta;
    });
  }
  
  selecionaItem(){
    this.listaPerguntaEvent.emit(this.listaPergunta);
  }

}
