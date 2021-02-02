import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import { Evento } from 'src/app/dominios/evento';
import { InscricaoResposta } from 'src/app/dominios/InscricaoResposta';
import { Pergunta } from 'src/app/dominios/pergunta';
import { PreInscricao } from 'src/app/dominios/PreInscricao';
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

  respostas:InscricaoResposta[] = [];

  perguntas:Pergunta[] = [];

  inscricao:PreInscricao = new PreInscricao();

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

    this.inscricao.idEvento = this.evento.id;
    this.inscricao.idUsuario = 1;
    this.inscricao.idSituacao = 1;
  }

  salvarResposta(respostas){

    for(let i in respostas){
      let index:number = Number.parseInt(i.slice(1))-1;
      let resposta:InscricaoResposta = new InscricaoResposta();
      resposta.idPergunta = this.perguntas[index].id;
      resposta.idEvento = this.evento.id;
      resposta.resposta = respostas[i].resposta;
      this.respostas[index] = resposta;
      console.log(this.respostas);
    }
  }

  enviarRespostas(id){
    for(let i in this.respostas){
      this.respostas[i].idInscricao = id;
      this.servico.salvarResposta(this.respostas[i])
      .subscribe(() => {
        console.log(this.respostas[i]);
      })
    }
  }

  enviarInscricao(){
    this.servico.criarInscricao(this.inscricao)
    .subscribe((inscricao) => {
      this.inscricao = inscricao
      console.log(this.inscricao)
      this.enviarRespostas(this.inscricao.id);
    });


    this.closeDialog();
  }

}
