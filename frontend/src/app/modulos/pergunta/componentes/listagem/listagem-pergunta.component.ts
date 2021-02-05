import { Component, OnInit } from '@angular/core';
import { ConfirmationService, MessageService } from 'primeng';
import { Pergunta } from 'src/app/dominios/pergunta';
import { PerguntaService } from '../../services/pergunta.service';

@Component({
  selector: 'app-listagem',
  templateUrl: './listagem-pergunta.component.html',
  styleUrls: ['./listagem-pergunta.component.css'],
})

export class ListagemComponent implements OnInit {

  exibirDialog = false;
  pergunta: Pergunta[] = [];
  listaIdPergunta: Pergunta[];
  formEdicao: boolean;
  pergunta_one: Pergunta = new Pergunta();

  constructor(
    private messageService: MessageService,
    private servico: PerguntaService,
    private confirmationService: ConfirmationService) { }

  ngOnInit(): void {
    this.buscarPergunta();
  }

  private buscarPergunta(){
    this.servico.getPergunta()
    .subscribe((pergunta: Pergunta[]) => {
    this.pergunta = pergunta;
    });
  }

  deletarPergunta(id: number) {
    this.servico.deletarPergunta(id)
      .subscribe(() => {
        this.addSingleSuccess('Pergunta deletada','success')
        this.buscarPergunta();
      },
      err => {this.addSingleSuccess(err,'error')});
  }

  dialogDeletarPergunta(id: number){
    this.confirmationService.confirm({
      message: 'Deseja excluir a Pergunta? ',
      accept: () => {
        this.deletarPergunta(id);
      }
    });
  }

  mostrarDialog(edicao = false) {
    
    this.exibirDialog = true;
    this.formEdicao = edicao;
  }

  mostrarDialogSalvar(){
    this.pergunta_one = new Pergunta();
    this.mostrarDialog()
  }

  fecharDialog(perguntaSalva: Pergunta) {
    console.log(perguntaSalva);
    this.exibirDialog = false; 
    this.buscarPergunta();
  }

  addSingleSuccess(detalhes: string,tipo: string) {
    this.messageService.add({severity:tipo, summary:'Mensagem de Serviço', detail:detalhes});
  }
}
