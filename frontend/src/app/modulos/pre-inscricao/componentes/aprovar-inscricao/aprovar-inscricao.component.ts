import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { InscricaoResposta } from 'src/app/dominios/InscricaoResposta';
import { Pergunta } from 'src/app/dominios/pergunta';
import { PreInscricao } from 'src/app/dominios/PreInscricao';
import { PerguntaResposta } from '../../dto/Conjunto';
import { InscricaoService } from '../../services/inscricao-service.service';

@Component({
  selector: 'app-aprovar-inscricao',
  templateUrl: './aprovar-inscricao.component.html',
  styleUrls: ['./aprovar-inscricao.component.css']
})
export class AprovarInscricaoComponent implements OnInit {

  @Input() id = 0;

  @Input() inscricao:PreInscricao = new PreInscricao();

  @Output() aprovarEvento = new EventEmitter();

  @Output() reprovarEvento = new EventEmitter();

  conjuntos:PerguntaResposta[] = [];

  constructor(
    private inscricaoService:InscricaoService
  ) { }

  ngOnInit(): void {
    this.inscricaoService.getInscricaoPorId(this.id).subscribe(inscricao => {
      this.inscricaoService.getRespostas(inscricao).subscribe( conjuntos => {
        this.conjuntos = conjuntos;
      })
    })
    
  }

  aprovarInscricao(){
    this.aprovarEvento.emit(this.id);
  }

  reprovarInscricao(){
    this.reprovarEvento.emit(this.id);
  }
}
