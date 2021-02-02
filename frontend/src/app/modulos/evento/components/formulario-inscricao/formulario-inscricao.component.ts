import { Component, Input, OnInit, Output } from '@angular/core';
import { Evento } from 'src/app/dominios/evento';
import { Pergunta } from 'src/app/dominios/pergunta';
import { ServicoPergutaService } from '../../services/servico-perguta.service';

@Component({
  selector: 'app-formulario-inscricao',
  templateUrl: './formulario-inscricao.component.html',
  styleUrls: ['./formulario-inscricao.component.css']
})
export class FormularioInscricaoComponent implements OnInit {

  @Input() inscricaoDialog:boolean = false;

  @Input() evento:Evento = new Evento();


  perguntas:Pergunta[] = [];

  constructor(
    private servico:ServicoPergutaService
    ) { }

  closeDialog(){
    this.inscricaoDialog = false;
  }

  ngOnInit(): void {
    console.log(this.evento.perguntas)
    this.evento.perguntas.forEach( 
      (pergunta) => {
        console.log(pergunta);
      } )
  }

}
