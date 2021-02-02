import { Component, OnInit } from '@angular/core';
import { Pergunta } from 'src/app/dominios/pergunta';
import { PerguntaService } from '../../services/pergunta.service';

@Component({
  selector: 'app-listagem',
  templateUrl: './listagem-pergunta.component.html',
  styleUrls: ['./listagem-pergunta.component.css'],
})
export class ListagemComponent implements OnInit {

  pergunta: Pergunta[] = [];

  constructor(private servico: PerguntaService) { }

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
}
