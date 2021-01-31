import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { EventoRoutingModule } from './evento-routing.module';
import { FormEventoComponent } from './components/form-evento/form-evento.component';
import { ListaEventoComponent } from './components/lista-evento/lista-evento.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { EventoService } from './services/evento-service.service';
import { HttpClientModule } from '@angular/common/http';
import { TipoEventoService } from './services/tipo-evento-service.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [FormEventoComponent, ListaEventoComponent],
  providers: [EventoService,TipoEventoService],
  imports: [
    CommonModule,
    EventoRoutingModule,
    SharedModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class EventoModule { }
