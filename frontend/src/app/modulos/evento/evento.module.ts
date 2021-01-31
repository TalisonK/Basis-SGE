import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { EventoRoutingModule } from './evento-routing.module';
import { FormEventoComponent } from './components/form-evento/form-evento.component';
import { ListaEventoComponent } from './components/lista-evento/lista-evento.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { EventoService } from './services/evento-service.service';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [FormEventoComponent, ListaEventoComponent],
  providers: [EventoService],
  imports: [
    CommonModule,
    EventoRoutingModule,
    SharedModule,
    HttpClientModule,

  ]
})
export class EventoModule { }
