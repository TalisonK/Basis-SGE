import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { EventoRoutingModule } from './evento-routing.module';
import { FormEventoComponent } from './form-evento/form-evento.component';


@NgModule({
  declarations: [FormEventoComponent],
  imports: [
    CommonModule,
    EventoRoutingModule
  ]
})
export class EventoModule { }
