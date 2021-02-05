  import { HttpErrorResponse } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { MessageService } from 'primeng/api';
  import { InscricaoListagem } from 'src/app/dominios/InscricaoListagem';
  import { PreInscricao } from 'src/app/dominios/PreInscricao';
  import { InscricaoService } from '../../services/inscricao-service.service';

  @Component({
    selector: 'app-listagem-inscricao',
    templateUrl: './listagem-inscricao.component.html',
    styleUrls: ['./listagem-inscricao.component.css']
  })
  export class ListagemInscricaoComponent implements OnInit {

    @Input() edicao = true;
    @Input() inscricao = new PreInscricao();

    inscricoes: InscricaoListagem[] = [];

    constructor(
      private service:InscricaoService,
      private messageService:MessageService
      ) { }

    ngOnInit(): void {
      this.buscarUsuarioInscricoes();
    }

    buscarUsuarioInscricoes() {
      this.service.getInscricao().subscribe((inscricoes: InscricaoListagem[]) =>{
        this.inscricoes = inscricoes;
      });
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
}