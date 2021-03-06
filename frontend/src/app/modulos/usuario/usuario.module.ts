import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UsuarioRoutingModule } from './usuario-routing.module';
import { FormularioComponent } from './componentes/formulario/formulario.component';
import { ListagemComponent } from './componentes/listagem/listagem.component';
import { UsuarioService } from './services/usuario.service';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { SharedModule } from 'src/app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { EdicaoUsuarioComponent } from './componentes/edicao-usuario/edicao-usuario.component';


@NgModule({
  declarations: [FormularioComponent, ListagemComponent, EdicaoUsuarioComponent],

  providers:[
    UsuarioService
  ],
  imports: [
    CommonModule,
    UsuarioRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    SharedModule,
    HttpClientModule
  ],schemas:[CUSTOM_ELEMENTS_SCHEMA]
})
export class UsuarioModule { }
