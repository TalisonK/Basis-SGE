import { Component, OnInit } from '@angular/core';
import { PreInscricao } from 'src/app/dominios/PreInscricao';
import { InscricaoService } from '../../services/inscricao-service.service';

@Component({
  selector: 'app-formulario-inscricao',
  templateUrl: './formulario.component.html',
  styleUrls: ['./formulario.component.css']
})
export class FormularioComponent implements OnInit {

	incricoes:PreInscricao[] = [];

	display: boolean = false;

	constructor(private service: InscricaoService) { }

	showDialog():void {
	this.display = !this.display;
	}

	ngOnInit(): void {
	}
}