import { Component, OnInit } from '@angular/core';
import { Evento} from 'src/app/dominios/evento';
import { EventoListagem } from '../../services/dto/evento-listagem';
import { EventoService } from '../../services/evento-service.service';
@Component({
  selector: 'app-lista-evento',
  templateUrl: './lista-evento.component.html',
  styleUrls: ['./lista-evento.component.css']
})
export class ListaEventoComponent implements OnInit {

  eventos: EventoListagem[] = [];

  constructor(private servico: EventoService) {}

  ngOnInit(): void {
    this.buscarEventos();

  }
  private buscarEventos(){
    this.servico.getEventos()
    .subscribe((eventos: EventoListagem[]) =>{
      this.eventos = eventos;
    });
  }

}