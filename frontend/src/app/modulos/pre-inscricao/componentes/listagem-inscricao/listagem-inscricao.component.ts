import { Component, Input, OnInit } from '@angular/core';
import { MessageService } from 'primeng/api';
import { InscricaoListagem } from 'src/app/dominios/InscricaoListagem';
import { PreInscricao } from 'src/app/dominios/PreInscricao';
import { PerguntaResposta } from '../../dto/Conjunto';
import { InscricaoService } from '../../services/inscricao-service.service';

@Component({
  selector: 'app-listagem-inscricao',
  templateUrl: './listagem-inscricao.component.html',
  styleUrls: ['./listagem-inscricao.component.css']
})
export class ListagemInscricaoComponent implements OnInit {

  condicaoAdmin = false;

  id:number = 0;

  inscricao:PreInscricao = new PreInscricao();

  conjuntos:PerguntaResposta[] = []

  aprovacaoDialog = false;

  inscricoes: InscricaoListagem[] = [];

  loading = '';

  constructor(
    private service:InscricaoService,
    private messageService:MessageService
    ) { }

  ngOnInit(): void {

    this.condicaoAdmin = JSON.parse(localStorage.getItem("usuario")).id == 1 ? true : false;
    console.log(JSON.parse(localStorage.getItem("usuario")))

    this.buscarUsuarioInscricoes();      
  }

  buscarUsuarioInscricoes() {
    this.service.getInscricao().subscribe((inscricoes: InscricaoListagem[]) =>{
      this.inscricoes = inscricoes;
    });
  }

  aprovarInscricao(id: number){
    console.log(id)
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