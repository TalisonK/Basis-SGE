import { Component, OnInit,Input ,Output ,EventEmitter} from '@angular/core';
import { TipoEvento } from 'src/app/dominios/tipo-evento';
import { TipoEventoService } from 'src/app/modulos/evento/services/tipo-evento-service.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Evento} from "src/app/dominios/evento"
import { EventoService } from '../../services/evento-service.service';
import { HttpErrorResponse } from '@angular/common/http'; 
import { EventoPergunta } from 'src/app/dominios/eventoPergunta';
import { Pergunta } from 'src/app/dominios/pergunta';
import { MessageService } from 'primeng/api';


@Component({
  selector: 'app-form-evento',
  templateUrl: './form-evento.component.html',
  styleUrls: ['./form-evento.component.css']
})
export class FormEventoComponent implements OnInit {

  @Input() edicao = false;

  @Input() evento = new Evento();
  
  @Input() categorias: TipoEvento[] = [];
  
  @Input() tipoEvento = new TipoEvento(); 

  listaEventoPergunta: EventoPergunta[] = [];
  @Input() listaPerguntas: Pergunta[];
  
  @Output() eventoSalvo = new EventEmitter<Evento>();
  
  form: FormGroup;
  
  constructor(
  
    private messageService: MessageService,

    private fb: FormBuilder,
  
    private servicoEvento: EventoService,
  
    private servicoTipoEvento: TipoEventoService,
  
    private route: ActivatedRoute
    ) {}

  ngOnInit(): void {
    this.route.params.subscribe(params =>{
      if(params.id){
        this.edicao = true
        this.obterEventoPorId(params.id);      
      }
    });
    this.form = this.fb.group({
      titulo: ['', Validators.minLength(3)],
      dataInicio: ['',Validators.required],
      dataFim: ['',Validators.required],
      descricao: '',
      local: '',
      quantVagas: 0,
      valor: 0.0,
      tipoInscricao:[Validators.nullValidator],
      idTipoEvento: [0,Validators.required],
      perguntas: []
    });
    this.buscarTipoEventos();
  }

  private buscarTipoEventos(){
    this.servicoTipoEvento.getTiposEventos()
    .subscribe((tipoEventos: TipoEvento[]) =>{
      this.categorias = tipoEventos;
    });
  }

  private obterTipoEventoPorId(id: number){
    this.servicoTipoEvento.obterTipoEventoPorId(id)
        .subscribe((tipoEvento: TipoEvento) => {
          this.tipoEvento = tipoEvento
        });
        
  }

  private obterEventoPorId(id: number){
    this.servicoEvento.obterEventoPorId(id).subscribe((evento: Evento) => {
      this.evento = evento
      this.obterTipoEventoPorId(evento.idTipoEvento)
    });
  }
  
  criar(){
   
    if(this.form.invalid){
      this.addSingleSuccess('Formulario Invalido!',"info");
      return;
    }
   
    if (this.edicao) {
      this.getIdTipoEvento()
      this.adicionarIdEventoEmEventoPergunta()
      if(!this.validarDatas()){return};
      this.servicoEvento.editarEvento(this.evento)
        .subscribe(evento => {
          this.addSingleSuccess('Evento Editado com Sucesso!',"success")
          this.fecharDialog(evento);
        }, (erro: HttpErrorResponse) => {
          this.addSingleSuccess(erro.error.message,"error")
        });
    } else {
      this.getIdTipoEvento()
      if(this.evento.tipoInscricao == null){
        this.evento.tipoInscricao = false
      }
      if(!this.validarDatas()){return};
      this.servicoEvento.salvarEvento(this.evento)
        .subscribe(evento => {
          this.addSingleSuccess('Evento Salvo com Sucesso!',"success")
          this.fecharDialog(evento);
        }, (erro: HttpErrorResponse) => {
          this.addSingleSuccess(erro.error.message,"error")
         
        });
      }
    }

  validarDatas(): boolean{
    if(!(this.evento.dataInicio<this.evento.dataFim)){
      this.addSingleSuccess("Duração Invalida",'info')
      return false;
    }
    let dataAgora: Date = new Date()
    if((dataAgora<this.evento.dataInicio)){
      this.addSingleSuccess("Duração Invalida!",'info')
      return false;
    }
    return true;
  }

  gerarListaEventoPergunta(listaPerguntas: Pergunta[]){
    listaPerguntas.forEach(element => {
      let eventoPergunta = new EventoPergunta();
      eventoPergunta.idPergunta =element.id;
      eventoPergunta.idEvento = null;
      this.listaEventoPergunta.push(eventoPergunta);
    });
    this.evento.perguntas=this.listaEventoPergunta;
    console.log(this.evento.perguntas)
    this.listaEventoPergunta = []
  }

  getIdTipoEvento(){
    this.evento.idTipoEvento = this.tipoEvento.id
  }

  adicionarIdEventoEmEventoPergunta(){
    this.evento.perguntas.forEach(element => {
      element.idEvento=this.evento.id;
    })
  }

  fecharDialog(eventoSalvo: Evento) {
    this.eventoSalvo.emit(eventoSalvo);
  }

  addSingleSuccess(detalhes: string,tipo: string) {
    this.messageService.add({severity:tipo, summary:'Mensagem de Serviço', detail:detalhes});
  }
}
