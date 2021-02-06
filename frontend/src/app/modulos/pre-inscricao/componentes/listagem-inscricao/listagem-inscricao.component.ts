import { HttpErrorResponse } from '@angular/common/http';
import { Component, Input, OnInit, Output,EventEmitter } from '@angular/core';
import { ConfirmationService, MessageService } from 'primeng';
import { InscricaoListagem } from 'src/app/dominios/InscricaoListagem';
import { PreInscricao } from 'src/app/dominios/PreInscricao';
import { Usuario } from 'src/app/dominios/usuario';
import { InscricaoService } from '../../services/inscricao-service.service';

@Component({
  selector: 'app-listagem-inscricao',
  templateUrl: './listagem-inscricao.component.html',
  styleUrls: ['./listagem-inscricao.component.css']
})
export class ListagemInscricaoComponent implements OnInit {

  exibirDialog = false;
  @Input() edicao = true;
  @Input() inscricao: PreInscricao = new PreInscricao();
  @Output() inscricaoCancelada = new EventEmitter<PreInscricao>();

  admin: boolean;

  private inscricoes: InscricaoListagem[] = [];

  constructor(private service: InscricaoService, private confirmationService: ConfirmationService, private messageService: MessageService) { }

  ngOnInit(): void {
    this.buscarUsuarioInscricoes();

    this.admin = JSON.parse(localStorage.getItem("usuario")).id == 1 ? true : false;

  }

  buscarUsuarioInscricoes() {
    this.service.getInscricao().subscribe((inscricoes: InscricaoListagem[]) => {
      this.inscricoes = inscricoes;
    });
  }

  aprovarInscricao(id: number) {
    this.service.getInscricaoPorId(id)
      .subscribe((inscricao: PreInscricao) => {
        this.aprovarInscricaoEditar(inscricao);
      })
  }

  aprovarInscricaoEditar(insc: PreInscricao) {
    console.log(insc);
    this.service.editarInscricao(insc).subscribe(inscricao => {
      alert('Inscrição aprovada');
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
  
  addSingle(error, sumary, detalhes) {
    this.messageService.add({ severity: error, summary: sumary, detail: detalhes });
  }

}