import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PreInscricaoRoutingModule } from './pre-inscricao-routing.module';
import { FormularioComponent } from './componentes/formulario/formulario.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { RespostaBlocoComponent } from './componentes/resposta-bloco/resposta-bloco.component';


@NgModule({
  declarations: [FormularioComponent, RespostaBlocoComponent],
  imports: [
    CommonModule,
    PreInscricaoRoutingModule,
    SharedModule
  ]
})
export class PreInscricaoModule { }
