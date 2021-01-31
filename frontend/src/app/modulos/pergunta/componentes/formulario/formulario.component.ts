import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Pergunta } from 'src/app/dominios/pergunta';
import { PerguntaService } from '../../services/pergunta.service';

@Component({
  selector: 'app-formulario',
  templateUrl: './formulario.component.html',
  styleUrls: ['./formulario.component.css']
})
export class FormularioComponent implements OnInit {

  formPergunta: FormGroup;
  edicao = false;
  pergunta = new Pergunta();
  

  constructor( 
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
          alert('Pergunta Editada')
        }, (erro: HttpErrorResponse) => {
          alert(erro.error.message);
        });
    } else {
      this.perguntaService.criarPergunta(this.pergunta).subscribe(pergunta => {
          alert('Pergunta Salva')
        }, (erro: HttpErrorResponse) => {
          alert(erro.error.message);
        });
    }
  }
}