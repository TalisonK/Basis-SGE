import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PreInscricaoRoutingModule } from './pre-inscricao-routing.module';
import { SharedModule } from 'src/app/shared/shared.module';
import { RespostaBlocoComponent } from './componentes/resposta-bloco/resposta-bloco.component';
import { InscricaoService } from './services/inscricao-service.service';
import { ListagemInscricaoComponent } from './componentes/listagem-inscricao/listagem-inscricao.component';
import { AprovarInscricaoComponent } from './componentes/aprovar-inscricao/aprovar-inscricao.component';
import { EventoService } from '../evento/services/evento-service.service';

@NgModule({
  declarations: [RespostaBlocoComponent, ListagemInscricaoComponent, AprovarInscricaoComponent],
  imports: [
    CommonModule,
    PreInscricaoRoutingModule,
    SharedModule
  ],
  providers:[InscricaoService, EventoService],
  schemas:[CUSTOM_ELEMENTS_SCHEMA]
})
export class PreInscricaoModule { }
