import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-formulario',
  templateUrl: './formulario.component.html',
  styleUrls: ['./formulario.component.css']
})
export class FormularioComponent implements OnInit {

  constructor() { }

  titulo:String = "Titulo do Evento"

  descricao:String = "Descrição do evento blablabla";

  inicio:Number = Date.now();

  Fim:Number = Date.now();

  display: boolean = false;

  showDialog() {
      this.display = !this.display;
  }

  ngOnInit(): void {
  }

}