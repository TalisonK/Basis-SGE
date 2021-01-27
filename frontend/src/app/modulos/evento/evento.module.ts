import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { EventoRoutingModule } from './evento-routing.module';
import { FormEventoComponent } from './form-evento/form-evento.component';
import { ListaEventoComponent } from './lista-evento/lista-evento.component';
import { InputTextModule } from 'primeng/inputtext';
import { SharedModule } from 'src/app/shared/shared.module';

@NgModule({
  declarations: [FormEventoComponent, ListaEventoComponent],
  imports: [
    CommonModule,
    EventoRoutingModule,
    SharedModule,
    InputTextModule
  ]
})
export class EventoModule { }
