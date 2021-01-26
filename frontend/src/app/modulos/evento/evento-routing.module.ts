import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FormEventoComponent } from './form-evento/form-evento.component';


const routes: Routes = [
  {
    path : '',
    component: FormEventoComponent 
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EventoRoutingModule { }
