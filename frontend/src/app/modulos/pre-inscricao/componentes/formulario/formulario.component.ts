import { HttpErrorResponse } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PreInscricao } from 'src/app/dominios/PreInscricao';
import { InscricaoService } from '../../services/inscricao-service.service';

@Component({
selector: 'app-formulario-inscricao',
templateUrl: './formulario.component.html',
styleUrls: ['./formulario.component.css']
})
export class FormularioComponent implements OnInit {

	@Input() edicao = false;
	@Input() inscricao = new PreInscricao();
	incricoes:PreInscricao[] = [];

	display: boolean = false;

	constructor(private service: InscricaoService, private route: ActivatedRoute){ }

	showDialog():void {
	this.display = !this.display;
	}

	ngOnInit(): void {

		this.route.params.subscribe(params => {
			if (params.id){
			this.edicao = true;
			this.buscarInscricao(params.id);}
		}
		)};


	buscarInscricao(id: number){
		this.service.getInscricaoPorId(id)
		.subscribe(inscricao => this.inscricao = this.inscricao);

		}
		
	aprovarInscricao(id: number){

		if(this.edicao){
			this.service.getInscricaoPorId(id).subscribe(inscricao =>{
				let inscricao1:PreInscricao = inscricao;
				this.aprovarInscricaoEditar(inscricao1.idSituacao= 2);
			}
			)};
	}

	aprovarInscricaoEditar(id: number){
			this.service.editarInscricao(this.inscricao).subscribe(inscricao =>{
				alert('Inscrição aprovada');
			}, (erro: HttpErrorResponse) => {
			alert(erro.error.message);
		})
	}
		
}
