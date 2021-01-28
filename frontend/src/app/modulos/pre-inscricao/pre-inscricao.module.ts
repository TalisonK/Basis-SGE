import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PreInscricaoRoutingModule } from './pre-inscricao-routing.module';
import { FormularioComponent } from './formulario/formulario.component';
import { SharedModule } from 'src/app/shared/shared.module';


@NgModule({
  declarations: [FormularioComponent],
  imports: [
    CommonModule,
    PreInscricaoRoutingModule,
    SharedModule
  ]
})
export class PreInscricaoModule { }
