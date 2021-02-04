import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PreInscricaoRoutingModule } from './pre-inscricao-routing.module';
import { FormularioComponent } from './componentes/formulario/formulario.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { RespostaBlocoComponent } from './componentes/resposta-bloco/resposta-bloco.component';
import { InscricaoService } from './services/inscricao-service.service';
import { ListagemInscricaoComponent } from './componentes/listagem-inscricao/listagem-inscricao.component';

@NgModule({
  declarations: [FormularioComponent, RespostaBlocoComponent, ListagemInscricaoComponent],
  imports: [
    CommonModule,
    PreInscricaoRoutingModule,
    SharedModule
  ],
  providers:[InscricaoService],
  schemas:[CUSTOM_ELEMENTS_SCHEMA]
})
export class PreInscricaoModule { }
