import { HttpErrorResponse } from '@angular/common/http';
import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Pergunta } from 'src/app/dominios/pergunta';
import { PerguntaService } from '../../services/pergunta.service';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-formulario',
  templateUrl: './formulario-pergunta.component.html',
  styleUrls: ['./formulario-pergunta.component.css']
})
export class FormularioComponent implements OnInit {

  formPergunta: FormGroup;
  edicao = false;
  pergunta = new Pergunta();
  @Output() perguntaSalva = new EventEmitter<Pergunta>();
  
  constructor( 
    private messageService: MessageService,
    private fb: FormBuilder, 
    private perguntaService: PerguntaService,
    private route: ActivatedRoute){}

  ngOnInit(): void {
    
    this.route.params.subscribe(params => {
      if(params.id){
        this.edicao = true;
        this.buscarPergunta(params.id);
      }
    });
    this.formPergunta = this.fb.group({
      titulo: ['', Validators.minLength(3)],
      obrigatoriedade:'',
    });
  }
  
  buscarPergunta(id: number){
    this.perguntaService.buscarPerguntaPorId(id).subscribe(pergunta => this.pergunta = pergunta);
  }

  criar(){   
    if(this.formPergunta.invalid){
      alert('Formulário inválido');
      return;
    }
    
    if (this.edicao) {
      this.perguntaService.editarPergunta(this.pergunta).subscribe(pergunta => {
        this.addSingleSuccess("Pergunta Editada!",'success')  
        this.fecharDialog(this.pergunta)
        }, (erro: HttpErrorResponse) => {
          this.addSingleSuccess(erro.error.message,'error')
        });
    } else {
      this.perguntaService.criarPergunta(this.pergunta).subscribe(pergunta => {
        this.addSingleSuccess("Pergunta Salva!",'success')
        this.fecharDialog(this.pergunta)
        }, (erro: HttpErrorResponse) => {
          this.addSingleSuccess(erro.error.message,'error')
          
        });
    }
  }
  
  fecharDialog(perguntaSalva: Pergunta) {
    this.perguntaSalva.emit(perguntaSalva);
  }
  //'info';'error';'success'
  addSingleSuccess(detalhes: string,tipo: string) {
    this.messageService.add({severity:tipo, summary:'Mensagem de Serviço', detail:detalhes});
  }
}