import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FormularioComponent } from './componentes/formulario/formulario.component';
import { ListagemComponent } from './listagem/listagem.component';


const routes: Routes = [
  {
    path: '',
    component:ListagemComponent
  },
  {
    path: 'formulario',
    component:FormularioComponent
  }
];



@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UsuarioRoutingModule { }
