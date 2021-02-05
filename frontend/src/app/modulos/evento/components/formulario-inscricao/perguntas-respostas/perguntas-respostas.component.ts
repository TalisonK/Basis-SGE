import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Pergunta } from 'src/app/dominios/pergunta';

@Component({
  selector: 'app-perguntas-respostas',
  templateUrl: './perguntas-respostas.component.html',
  styleUrls: ['./perguntas-respostas.component.css']
})
export class PerguntasRespostasComponent implements OnInit {

  @Input() pergunta:Pergunta = new Pergunta();

  @Input() numero:Number = 0;

  @Output() enviarRespostaEvent = new EventEmitter();

  resposta:String = "";

  respostas = {};

  obrigatoriedade:string = "";

  constructor() { }

  ngOnInit(): void {
    this.obrigatoriedade = this.pergunta.obrigatoriedade?"*":"";
  }

  salvaResposta(pergunta, numero){
    this.respostas["c" + numero] = {
      pergunta:pergunta,
      resposta:this.resposta
    }
    this.enviarRespostaEvent.emit(this.respostas);
  }

}
