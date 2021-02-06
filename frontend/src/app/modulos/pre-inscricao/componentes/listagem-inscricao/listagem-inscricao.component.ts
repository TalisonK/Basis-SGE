import { HttpErrorResponse } from '@angular/common/http';
import { Component, Input, OnInit, Output,EventEmitter } from '@angular/core';
import { MessageService, ConfirmationService } from 'primeng/api';
import { InscricaoListagem } from 'src/app/dominios/InscricaoListagem';
import { PreInscricao } from 'src/app/dominios/PreInscricao';
import { Usuario } from 'src/app/dominios/usuario';
import { PerguntaResposta } from '../../dto/Conjunto';
import { InscricaoService } from '../../services/inscricao-service.service';


@Component({
  selector: 'app-listagem-inscricao',
  templateUrl: './listagem-inscricao.component.html',
  styleUrls: ['./listagem-inscricao.component.css']
})
export class ListagemInscricaoComponent implements OnInit {

  exibirDialog = false;
  condicaoAdmin = false;
      
  @Output() inscricaoCancelada = new EventEmitter<PreInscricao>();

  id:number = 0;

  inscricao:PreInscricao = new PreInscricao();

  conjuntos:PerguntaResposta[] = []

  aprovacaoDialog = false;

  inscricoes: InscricaoListagem[] = [];

  loading = '';

  constructor(
    private service:InscricaoService,
    private messageService:MessageService,
    private confirmationService: ConfirmationService
    ) { }

  ngOnInit(): void {

    this.condicaoAdmin = JSON.parse(localStorage.getItem("usuario")).id == 1 ? true : false;

    this.buscarUsuarioInscricoes();      
  }

  buscarUsuarioInscricoes() {
    this.service.getInscricao().subscribe((inscricoes: InscricaoListagem[]) =>{
      this.inscricoes = inscricoes;
    });
  }

  dialogCancelarInscricao(id: number) {
    this.confirmationService.confirm({
      message: 'Dejesa cancelar a sua inscrição no evento? ',
      accept: () => {
        this.cancelarInscricao(id);
      }
    });
  }

  cancelarInscricao(id: number) {
    this.service.cancelarInscricao(id)
    .subscribe(() => {
      this.addSingle("success", "Mensagem de Serviço", "Inscricao Cancelada");
      this.inscricaoCancelada.emit(this.inscricao);
    },
    err => this.addSingle("error","Mensagem de Serviço",err));
    this.service.getInscricaoPorIdUsuario(id).subscribe(inscricoes => {this.inscricoes = inscricoes});
  }

  aprovarInscricao(id: number){
    this.service.getInscricaoPorId(id)
    .subscribe((inscricao: PreInscricao) =>{
      inscricao.idSituacao = 2;
      this.aprovarInscricaoEditar(inscricao);
    })
  }

  aprovarInscricaoEditar(insc:PreInscricao){
    this.service.editarInscricao(insc).subscribe(inscricao =>{
      this.addSingle("success", "Inscrição aprovada","");
      this.buscarUsuarioInscricoes()
    });
    
  }

  addSingle(error,sumary, detalhes) {
    this.messageService.add({severity:error, summary:sumary, detail:detalhes});
  }

  reprovarInscricao(id){
    this.service.getInscricaoPorId(id).subscribe(
      (inscricao) => {
        inscricao.idSituacao = 3;
        this.service.editarInscricao(inscricao).subscribe(() => {
          this.addSingle("warn", "Inscrição reprovada","");
          this.buscarUsuarioInscricoes()
        })
      }
    )
  }

  openDialog(id){
    this.id = id;
    this.aprovacaoDialog = true;;
  }

  closeDialog(){
    this.aprovacaoDialog = false;
  }
}