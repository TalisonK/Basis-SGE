import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FormEventoComponent } from './components/form-evento/form-evento.component';
import { ListaEventoComponent} from './components/lista-evento/lista-evento.component'

const routes: Routes = [
  {
    path : 'form',
    component: FormEventoComponent 
  },
  {
    path : '',
    component: ListaEventoComponent 
  },
  {
    path: 'form/:id',
    component: FormEventoComponent
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EventoRoutingModule { }
