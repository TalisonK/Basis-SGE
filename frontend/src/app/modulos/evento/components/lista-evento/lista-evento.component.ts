import { Component, Input, OnInit, Output } from '@angular/core';
import { ConfirmationService } from 'primeng';
import { Evento } from 'src/app/dominios/evento';
import { EventoPergunta } from 'src/app/dominios/eventoPergunta';

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
  condicao = true;
  eventos: EventoListagem[] = [];
  evento = new Evento();
  exibirDialog = false;
  inscricaoDialog = false;
  formEdicao: boolean;
  tipoEvento = new TipoEvento();
  loading = '';
  

  constructor(

    private servico: EventoService,

    private servicoTipoEvento: TipoEventoService,

    private confirmationService: ConfirmationService

    ) {}

  ngOnInit(): void {

    this.buscarEventos();

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
    
        alert('Evento Excluido');
    
        this.buscarEventos();
      },
      err => alert(err));
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
    console.log(eventoSalvo);
    this.exibirDialog = false; 
    this.buscarEventos();
  }

  inscricao(id){
    this.servico.obterEventoPorId(id)
      .subscribe(evento => {
        this.evento = evento
        this.inscricaoDialog = !this.inscricaoDialog;
        console.log("oi");
      }); 
    
  }
  
  fecharInscricaoDialog(){
    this.inscricaoDialog = false;
  }

}
