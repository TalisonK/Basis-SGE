import { Component, OnInit} from '@angular/core';
import { Input,Output, EventEmitter } from '@angular/core';
import { Evento } from 'src/app/dominios/evento';
import { Pergunta } from 'src/app/dominios/pergunta';
import { PerguntaService } from 'src/app/modulos/pergunta/services/pergunta.service';
import { EventoService } from '../../services/evento-service.service';

@Component({
  selector: 'app-lista-pergunta',
  templateUrl: './lista-pergunta.component.html',
  styleUrls: ['./lista-pergunta.component.css']
})
export class ListaPerguntaComponent implements OnInit {

  pergunta: Pergunta[] = [];
  listaPergunta: Pergunta[] = [];
  @Output() listaPerguntaEvent = new EventEmitter<Pergunta[]>();
  @Input() editar = false; 
  @Input() eventoEditar: Evento;

  constructor(
    
    private servico: PerguntaService,

    private serviceEvento: EventoService
  
  ) { }

  ngOnInit(): void {
    this.buscarPergunta();
    this.listaPerguntaEvent.emit(this.listaPergunta);
  }

  private buscarPergunta(){
    this.servico.getPergunta()
    .subscribe((pergunta: Pergunta[]) => {
    this.pergunta = pergunta;
    });
  }
  selecionaPerguntaEventoSalvo(id: number){
    if(this.editar){
      this.serviceEvento.obterPerguntasPorIdEvento(id).subscribe((perguntas: Pergunta[])=>{
        this.listaPergunta = perguntas;
      });
    }
  }  
  selecionaItem(){
    this.listaPerguntaEvent.emit(this.listaPergunta);
  }

}
