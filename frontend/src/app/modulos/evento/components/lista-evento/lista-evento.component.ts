import { Component, Input, OnInit, Output } from '@angular/core';
import { ConfirmationService, MessageService } from 'primeng';
import { Evento } from 'src/app/dominios/evento';
import { Pergunta } from 'src/app/dominios/pergunta';
import { TipoEvento } from 'src/app/dominios/tipo-evento';
import { EventoListagem } from '../../services/dto/evento-listagem';
import { EventoService } from '../../services/evento-service.service';
import { TipoEventoService } from '../../services/tipo-evento-service.service';

@Component({
  selector: 'app-lista-evento',
  templateUrl: './lista-evento.component.html',
  styleUrls: ['./lista-evento.component.css']
})
export class ListaEventoComponent implements OnInit {

  @Input() categorias: TipoEvento[];
  condicao = JSON.parse(localStorage.getItem("usuario")).id == 1? true : false;;
  eventos: EventoListagem[] = [];
  evento = new Evento();
  exibirDialog = false;
  inscricaoDialog = false;
  formEdicao: boolean;
  tipoEvento = new TipoEvento();
  loading = '';
  perguntasEventoSalvo: Pergunta[] = []

  constructor(

    private servico: EventoService,

    private servicoTipoEvento: TipoEventoService,

    private confirmationService: ConfirmationService,

    private messageService: MessageService
    ) {}

  ngOnInit(): void {

    this.buscarEventos();

    this.condicao = JSON.parse(localStorage.getItem("usuario")).id == 1? true : false;

  }

  private buscarEventos(){

    this.servico.getEventos()
    .subscribe((eventos: EventoListagem[]) =>{
    
      this.eventos = eventos;
    
    });

  }

  confirmarDeletarEvento(id: number) {
      this.confirmationService.confirm({
    
        message: 'Tem certeza que deseja excluir este Evento?',
    
          accept: () => {
    
            this.deletarEvento(id);
    
          }
      });
    }

  deletarEvento(id?: number) {
    this.servico.deletarEvento(id)
      .subscribe(() => {
    
        this.addSingleSuccess("Evento Deletado","success")
    
        this.buscarEventos();
      },
      err => {this.addSingleSuccess("Erro ao deletar o evento","error")});
  }

  mostrarDialogEditar(id: number) {
    this.servico.obterEventoPorId(id)
      .subscribe(evento => {
    
        this.evento = evento
    
        this.servicoTipoEvento.obterTipoEventoPorId(evento.idTipoEvento)
        .subscribe((tipoEvento: TipoEvento) => {this.tipoEvento = tipoEvento});

        this.mostrarDialog(true);
      }); 
  }

  mostrarDialog(edicao = false) {
    
    this.exibirDialog = true;
    this.formEdicao = edicao;
  }

  mostrarDialogSalvar(){
    this.tipoEvento = new TipoEvento();
    this.evento = new Evento();
    this.mostrarDialog()
  }

  fecharDialog(eventoSalvo: Evento) {
    this.exibirDialog = false; 
    this.buscarEventos();
  }

  inscricao(id){
    this.servico.obterEventoPorId(id)
      .subscribe(evento => {
        this.evento = evento
        this.inscricaoDialog = !this.inscricaoDialog;
      });
  }
  
  fecharInscricaoDialog(){
    this.inscricaoDialog = false;
  }

  addSingleSuccess(detalhes: string,tipo: string) {
    this.messageService.add({severity:tipo, summary:'Mensagem de Serviço', detail:detalhes});
  }
  
  addMultiple() {
      this.messageService.addAll([{severity:'success', summary:'Service Message', detail:'Via MessageService'},
                                  {severity:'info', summary:'Info Message', detail:'Via MessageService'}]);
  }

  clear() {
      this.messageService.clear();
  }
}
