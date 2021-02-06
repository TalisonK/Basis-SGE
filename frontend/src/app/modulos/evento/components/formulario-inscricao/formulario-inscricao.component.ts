import { HttpErrorResponse } from '@angular/common/http';
import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import { MessageService } from 'primeng/api';
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

  msgs

  constructor(
    private servico:ServicoPergutaService,
    private messageService: MessageService
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

    for(let j in respostas){
      let index:number = Number.parseInt(j.slice(1))-1;
      if(respostas[j].resposta == "" && this.perguntas[index].obrigatoriedade){
        this.addSingle("error","necessita de uma resposta", "pergunta "+ index + 1);
        return;
      }
    }

    for(let i in respostas){
      let index:number = Number.parseInt(i.slice(1))-1;
      let resposta:InscricaoResposta = new InscricaoResposta();
      resposta.idPergunta = this.perguntas[index].id;
      resposta.idEvento = this.evento.id;
      resposta.resposta = respostas[i].resposta;
      this.respostas[index] = resposta;
    }
  }

  enviarRespostas(id){

    for(let i in this.respostas){
      this.respostas[i].idInscricao = id;
      this.servico.salvarResposta(this.respostas[i])
      .subscribe(() => {
      })
    }
  }

  enviarInscricao(){

    let cond = true;

    let quantObrigatorias = 0;
    this.perguntas.forEach((pergunta) => {
      pergunta.obrigatoriedade?quantObrigatorias++:null;
    })

    if(quantObrigatorias > 0) {
      this.perguntas.forEach((pergunta) => {

        for(let chave in this.respostas){
          if(pergunta.id == this.respostas[chave].idPergunta && pergunta.obrigatoriedade && this.respostas[chave].resposta != ""){
            return;
          }
        }
        if(pergunta.obrigatoriedade) cond = false;
      })
    }
    
    if(cond){
      this.servico.criarInscricao(this.inscricao)
      .subscribe((inscricao) => {
        this.inscricao = inscricao
        this.enviarRespostas(this.inscricao.id);
        this.closeDialog();
        this.addSingle("success", "Inscrição bem sucedida!","");
      }, (erro: HttpErrorResponse) => {
        this.addSingle("error", erro.error.message, "");
      });
      
    }
    else{
      this.addSingle("error", "Responda todas as questões obrigatórias!", "");
    }
  }

  addSingle(error,sumary, detalhes) {
    this.messageService.add({severity:error, summary:sumary, detail:detalhes});
  }

  clear() {
      this.messageService.clear();
  }

  
}
