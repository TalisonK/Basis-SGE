import { Component, OnInit } from '@angular/core';
import { ConfirmationService } from 'primeng';
import { EventoListagem } from '../../services/dto/evento-listagem';
import { EventoService } from '../../services/evento-service.service';

@Component({
  selector: 'app-lista-evento',
  templateUrl: './lista-evento.component.html',
  styleUrls: ['./lista-evento.component.css']
})
export class ListaEventoComponent implements OnInit {

  eventos: EventoListagem[] = [];

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

  // confirmarDeletarEvento(id: number) {
  //   this.confirmationService.confirm({
  //       message: 'Tem certeza que deseja excluir este Evento?',
  //       accept: () => {
  //         this.deletarEvento(id);
  //       }
  //   });
  // }

  deletarEvento(id?: number) {
    this.servico.deletarEvento(id)
      .subscribe(() => {
        alert('Evento Excluido');
        this.buscarEventos();
      },
      err => alert(err));
  }
}
