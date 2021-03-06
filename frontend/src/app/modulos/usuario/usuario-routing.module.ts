import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { EdicaoUsuarioComponent } from './componentes/edicao-usuario/edicao-usuario.component';
import { FormularioComponent } from './componentes/formulario/formulario.component';
import { ListagemComponent } from './componentes/listagem/listagem.component';


const routes: Routes = [
  {
    path: '',
    component:ListagemComponent
  },
  {
    path: 'formulario',
    component:FormularioComponent
  },
  {
    path: 'formulario/:id',
    component: FormularioComponent
  },
  {
    path: 'editar',
    component: EdicaoUsuarioComponent
  }
  
  
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UsuarioRoutingModule { }
