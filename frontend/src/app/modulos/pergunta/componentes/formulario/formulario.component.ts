import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Pergunta } from 'src/app/dominios/pergunta';
import { PerguntaService } from '../../services/pergunta.service';

@Component({
  selector: 'app-formulario',
  templateUrl: './formulario.component.html',
  styleUrls: ['./formulario.component.css']
})

export class FormularioComponent implements OnInit {

  formPergunta: FormGroup;
  pergunta = new Pergunta();

  constructor( private fb: FormBuilder, private perguntaService: PerguntaService) { }

  ngOnInit(): void {

    this.formPergunta = this.fb.group({
      titulo: ['', Validators.minLength(1)],
      obrigatoriedade: '',
    })
  }

  criar(){
    if(this.formPergunta.invalid){
      alert('Inválido');
      return;
    }

    this.perguntaService.criarPergunta(this.pergunta).subscribe(pergunta => {
      console.log('salvou', pergunta);
      alert('Pergunta salva')
    }, (erro: HttpErrorResponse) => {
      alert(erro.message);
    })
  }
}
