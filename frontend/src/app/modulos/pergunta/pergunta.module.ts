import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PerguntaRoutingModule } from './pergunta-routing.module';
import { FormularioComponent } from './componentes/formulario/formulario.component';
import { ListagemComponent } from '../usuario/listagem/listagem.component';
import { PerguntaService } from './services/pergunta-service';
import { HttpClientModule } from '@angular/common/http';
import { SharedModule } from 'primeng';

@NgModule({
  declarations: [FormularioComponent, ListagemComponent],
  
  providers:[
    PerguntaService
  ],

  imports: [
    CommonModule,
    PerguntaRoutingModule,
    SharedModule,
    HttpClientModule
  ]
})
export class PerguntaModule { }
