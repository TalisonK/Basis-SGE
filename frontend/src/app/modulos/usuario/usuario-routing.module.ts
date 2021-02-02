import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FormularioComponent } from './componentes/formulario/formulario.component';
import { ListagemComponent } from './componentes/listagem/listagem.component';
import { LoginComponent } from './componentes/login/login.component';


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
  
  
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UsuarioRoutingModule { }
