import { Component, OnInit } from '@angular/core';
import { ConfirmationService } from 'primeng';
import { Evento } from 'src/app/dominios/evento';
import { EventoListagem } from '../../services/dto/evento-listagem';
import { EventoService } from '../../services/evento-service.service';

@Component({
  selector: 'app-lista-evento',
  templateUrl: './lista-evento.component.html',
  styleUrls: ['./lista-evento.component.css']
})
export class ListaEventoComponent implements OnInit {

  eventos: EventoListagem[] = [];
  evento = new Evento();
  exibirDialog = false;
  edicao: boolean;

  constructor(

    private servico: EventoService,

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
        this.mostrarDialog(true);
      }); 
  }

  mostrarDialog(edicao = false) {
    this.exibirDialog = true;
    this.edicao = edicao;
  }

  fecharDialog(eventoSalvo: Evento) {
    console.log(eventoSalvo);
    this.exibirDialog = false;
    this.buscarEventos();
  }

}
