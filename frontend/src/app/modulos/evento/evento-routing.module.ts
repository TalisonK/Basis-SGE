import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FormEventoComponent } from './form-evento/form-evento.component';
import { ListaEventoComponent} from './lista-evento/lista-evento.component'

const routes: Routes = [
  {
    path : 'form',
    component: FormEventoComponent 
  },
  {
    path : '',
    component: ListaEventoComponent 
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EventoRoutingModule { }
