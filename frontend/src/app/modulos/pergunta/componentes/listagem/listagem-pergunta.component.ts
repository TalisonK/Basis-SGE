import { Component, OnInit } from '@angular/core';
import { ConfirmationService } from 'primeng';
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
  checked: Boolean = false;

  constructor(
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
        alert('Pergunta deletada');
        this.buscarPergunta();
      },
      err => alert(err));
  }

  dialogDeletarPergunta(id: number){
    this.confirmationService.confirm({
      message: 'Deseja excluir a Pergunta? ',
      accept: () => {
        this.deletarPergunta(id);
      }
    });
  }
}
