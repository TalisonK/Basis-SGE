import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit} from '@angular/core';
import { Input,Output, EventEmitter } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Evento } from 'src/app/dominios/evento';
import { Pergunta } from 'src/app/dominios/pergunta';
import { PerguntaService } from 'src/app/modulos/pergunta/services/pergunta.service';
import { EventoService } from '../../services/evento-service.service';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-lista-pergunta',
  templateUrl: './lista-pergunta.component.html',
  styleUrls: ['./lista-pergunta.component.css']
})
export class ListaPerguntaComponent implements OnInit {

  perguntas: Pergunta[] = [];
  pergunta: Pergunta = new Pergunta();
  listaPergunta: Pergunta[] = [];
  @Output() listaPerguntaEvent = new EventEmitter<Pergunta[]>();
  @Input() editar: boolean; 
  @Input() eventoEditar: Evento;
  formPergunta: FormGroup;

  constructor(
    private messageService: MessageService,

    private servico: PerguntaService,

    private serviceEvento: EventoService,
    
    private fb: FormBuilder
  ) { }

  ngOnInit(): void {
    this.buscarPergunta();
    this.listaPerguntaEvent.emit(this.listaPergunta);
    this.formPergunta = this.fb.group({
      titulo: ['', Validators.minLength(3)],
      obrigatoriedade:'',
    });
    this.pergunta = new Pergunta()
    this.selecionaPerguntaEventoSalvo();
  }

  private buscarPergunta(){
    this.servico.getPergunta()
    .subscribe((perguntas: Pergunta[]) => {
    this.perguntas = perguntas;
    });
  }
  selecionaPerguntaEventoSalvo(){
    if(this.editar){
      this.serviceEvento.obterPerguntasPorIdEvento(this.eventoEditar.id).subscribe((perguntas: Pergunta[])=>{
        this.listaPergunta = perguntas;
      });
      
    }
  }  
  selecionaItem(){
    this.listaPerguntaEvent.emit(this.listaPergunta);
  }

  criar(){   
    if(this.formPergunta.invalid){
      this.addSingleSuccess('Formulário inválido','info')
      return;
    }else {this.servico.criarPergunta(this.pergunta).subscribe(pergunta => { 
        this.addSingleSuccess('Pergunta Salva','success')
        this.buscarPergunta()
        }, (erro: HttpErrorResponse) => {
          this.addSingleSuccess(erro.error.message,'error')
        });
    }
  }

  addSingleSuccess(detalhes: string,tipo: string) {
    this.messageService.add({severity:tipo, summary:'Mensagem de Serviço', detail:detalhes});
  }
}
