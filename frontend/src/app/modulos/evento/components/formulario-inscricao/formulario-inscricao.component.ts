import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import { Evento } from 'src/app/dominios/evento';
import { InscricaoResposta } from 'src/app/dominios/InscricaoResposta';
import { Pergunta } from 'src/app/dominios/pergunta';
import { ServicoPergutaService } from '../../services/servico-perguta.service';

@Component({
  selector: 'app-formulario-inscricao',
  templateUrl: './formulario-inscricao.component.html',
  styleUrls: ['./formulario-inscricao.component.css']
})
export class FormularioInscricaoComponent implements OnInit {

  @Input() inscricaoDialog:boolean = false;

  @Input() evento:Evento = new Evento();

  @Output() dialogEvento = new EventEmitter();

  respostas = {};

  perguntas:Pergunta[] = [];

  constructor(
    private servico:ServicoPergutaService
    ) { }

  closeDialog(){
    this.inscricaoDialog = false;
    this.dialogEvento.emit(null);
  }

  ngOnInit(): void {
    this.evento.perguntas.forEach( (pergunta) => 
      {
        this.servico.buscarPergunta(pergunta.idPergunta)
        .subscribe((pergunta:Pergunta) => {this.perguntas.push(pergunta)});
      } 
    )
  }

  EnviarResposta(respostas){

    for(let i in respostas){this.respostas[this.perguntas[respostas[i].numero-1].id] = respostas[i]};
    console.log(this.respostas);
  }

  enviarInscricao(){

    


    this.closeDialog();
  }

}
