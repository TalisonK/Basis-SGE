import { Component, OnInit } from '@angular/core';
import { TipoEvento } from 'src/app/dominios/tipo-evento';
import { TipoEventoService } from 'src/app/modulos/evento/services/tipo-evento-service.service';

@Component({
  selector: 'app-form-evento',
  templateUrl: './form-evento.component.html',
  styleUrls: ['./form-evento.component.css']
})
export class FormEventoComponent implements OnInit {

  categorias: TipoEvento[] = [];

  constructor(private servico: TipoEventoService) { }

  ngOnInit(): void {
    this.buscarTipoEventos();
  }

  private buscarTipoEventos(){
    this.servico.getTiposEventos()
    .subscribe((tipoEventos: TipoEvento[]) =>{
      this.categorias = tipoEventos;
    });
  }

}
