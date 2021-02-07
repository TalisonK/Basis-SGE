import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PerguntaRoutingModule } from './pergunta-routing.module';
import { FormularioComponent } from './componentes/formulario/formulario-pergunta.component';
import { ListagemComponent } from './componentes/listagem/listagem-pergunta.component';
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
  ],
  exports: [
    PerguntaService
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PerguntaModule { }
