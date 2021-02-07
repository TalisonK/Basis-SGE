import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ListagemInscricaoComponent } from './componentes/listagem-inscricao/listagem-inscricao.component';

const routes: Routes = [
	{
		path:'',
		component: ListagemInscricaoComponent
	}
];
 
@NgModule({
	imports: [RouterModule.forChild(routes)],
	exports: [RouterModule]
})
export class PreInscricaoRoutingModule { }
