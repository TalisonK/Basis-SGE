import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-perguntas-respostas',
  templateUrl: './perguntas-respostas.component.html',
  styleUrls: ['./perguntas-respostas.component.css']
})
export class PerguntasRespostasComponent implements OnInit {

  @Input() pergunta:String = "";

  @Input() numero:Number = 0;

  @Output() enviarRespostaEvent = new EventEmitter();

  resposta:String = "";

  respostas = {};

  constructor() { }

  ngOnInit(): void {
  }

  salvaResposta(pergunta, numero){
    this.respostas["c" + numero] = {
      numero:numero,
      pergunta:pergunta,
      resposta:this.resposta
    }
    this.enviarRespostaEvent.emit(this.respostas);
  }

}
