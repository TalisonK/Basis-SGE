import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FormularioComponent } from './componentes/formulario/formulario-pergunta.component';
import { ListagemComponent } from './componentes/listagem/listagem-pergunta.component';

const routes: Routes = [
  {
    path : 'formulario',
    component: FormularioComponent
  },
  {
    path : 'formulario/:id',
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

