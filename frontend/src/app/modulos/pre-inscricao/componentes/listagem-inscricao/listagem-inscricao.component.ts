  import { HttpErrorResponse } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
  import { InscricaoListagem } from 'src/app/dominios/InscricaoListagem';
  import { PreInscricao } from 'src/app/dominios/PreInscricao';
  import { InscricaoService } from '../../services/inscricao-service.service';

  @Component({
    selector: 'app-listagem-inscricao',
    templateUrl: './listagem-inscricao.component.html',
    styleUrls: ['./listagem-inscricao.component.css']
  })
  export class ListagemInscricaoComponent implements OnInit {

    @Input() edicao = true;
    @Input() inscricao = new PreInscricao();

    private inscricoes: InscricaoListagem[] = [];

    constructor(private service:InscricaoService) { }

    ngOnInit(): void {
      this.buscarUsuarioInscricoes();
    }

    buscarUsuarioInscricoes() {
      this.service.getInscricao().subscribe((inscricoes: InscricaoListagem[]) =>{
        this.inscricoes = inscricoes;
      });
    }

    aprovarInscricao(id: number){
      this.service.getInscricaoPorId(id)
      .subscribe((inscricao: PreInscricao) =>{
        this.aprovarInscricaoEditar(inscricao);
      })
    }

    aprovarInscricaoEditar(insc:PreInscricao){
      console.log(insc);
			this.service.editarInscricao(insc).subscribe(inscricao =>{
        console.log("cu")
				alert('Inscrição aprovada');
			});
	}
}