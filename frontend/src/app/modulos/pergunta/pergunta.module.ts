import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PerguntaRoutingModule } from './pergunta-routing.module';
import { FormularioComponent } from './componentes/formulario/formulario.component';


@NgModule({
  declarations: [FormularioComponent],
  imports: [
    CommonModule,
    PerguntaRoutingModule
  ]
})
export class PerguntaModule { }
