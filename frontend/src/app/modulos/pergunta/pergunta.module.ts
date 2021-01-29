import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PerguntaRoutingModule } from './pergunta-routing.module';
import { FormularioComponent } from './componentes/formulario/formulario.component';
import { ListagemComponent } from './componentes/listagem/listagem.component';
import { PerguntaService } from './services/pergunta.service';
import { HttpClientModule } from '@angular/common/http';
import { SharedModule } from 'src/app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [FormularioComponent, ListagemComponent],
  
  providers:[
    PerguntaService
  ],

  imports: [
    CommonModule,
    PerguntaRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    SharedModule,
    HttpClientModule
  ]
})
export class PerguntaModule { }
