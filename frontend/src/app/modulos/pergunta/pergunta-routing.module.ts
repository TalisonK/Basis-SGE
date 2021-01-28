import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FormularioComponent } from '../usuario/componentes/formulario/formulario.component';
import { ListagemComponent } from '../usuario/listagem/listagem.component';

const routes: Routes = [
  {
    path : 'formulario',
    component: FormularioComponent
  },
  {
    path : '',
    component: ListagemComponent
  }
  ];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PerguntaRoutingModule { }

