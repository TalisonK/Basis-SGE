import { Component, OnInit } from '@angular/core';
import { TipoEvento } from 'src/app/dominios/tipo-evento';
import { TipoEventoService } from 'src/app/modulos/evento/services/tipo-evento-service.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Evento} from "src/app/dominios/evento"
import { EventoService } from '../../services/evento-service.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-form-evento',
  templateUrl: './form-evento.component.html',
  styleUrls: ['./form-evento.component.css']
})
export class FormEventoComponent implements OnInit {

  edicao = false;
  form: FormGroup;
  evento = new Evento();
  categorias: TipoEvento[] = [];

  constructor(
    private fb: FormBuilder,
    private servicoEvento: EventoService,
    private servicoTipoEvento: TipoEventoService,
    private route: ActivatedRoute
    ) {}

  ngOnInit(): void {
    this.route.params.subscribe(params =>{
      if(params.id){
        this.edicao = true
        this.obterEventoPorId(params.id);
      }
    });
    this.form = this.fb.group({
      titulo: ['', Validators.minLength(3)],
      dataInicio: '',
      dataFim: '',
      descricao: '',
      local: '',
      quantVagas: 0,
      valor: 0.0,
      tipoInscricao: false,
      idTipoEvento: 1,
      perguntas: []
    });

    this.buscarTipoEventos();
  }

  private buscarTipoEventos(){
    this.servicoTipoEvento.getTiposEventos()
    .subscribe((tipoEventos: TipoEvento[]) =>{
      this.categorias = tipoEventos;
    });
  }
  private obterEventoPorId(id: number){
    this.servicoEvento.obterEventoPorId(id).subscribe(evento => this.evento = evento);
  }
  criar(){
   
    if(this.form.invalid){
      alert('Formulário inválido');
      return;
    }
   
    if (this.edicao) {
      this.servicoEvento.editarEvento(this.evento)
        .subscribe(usuario => {
          alert('Evento Editado com Sucesso')
        }, (erro: HttpErrorResponse) => {
          alert(erro.error.message);
        });
    } else {
      this.servicoEvento.salvarEvento(this.evento)
        .subscribe(evento => {
          alert('Evento Salvo com Sucesso!')
        }, (erro: HttpErrorResponse) => {
          alert(erro.error.message);
        });
      }
    }

}
