import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { EventoRoutingModule } from './evento-routing.module';
import { FormEventoComponent } from './components/form-evento/form-evento.component';
import { ListaEventoComponent } from './components/lista-evento/lista-evento.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { EventoService } from './services/evento-service.service';
import { HttpClientModule } from '@angular/common/http';
import { TipoEventoService } from './services/tipo-evento-service.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ConfirmationService } from 'primeng';
import { FormularioInscricaoComponent } from './components/formulario-inscricao/formulario-inscricao.component';
import { PerguntasRespostasComponent } from './components/formulario-inscricao/perguntas-respostas/perguntas-respostas.component';

@NgModule({
  declarations: [
    FormEventoComponent,
    ListaEventoComponent,
    FormularioInscricaoComponent,
    PerguntasRespostasComponent
    ],
  providers: [
    EventoService,
    TipoEventoService
  ],
  imports: [
    CommonModule,
    EventoRoutingModule,
    SharedModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  schemas:[
    CUSTOM_ELEMENTS_SCHEMA
  ]
})
export class EventoModule { }
