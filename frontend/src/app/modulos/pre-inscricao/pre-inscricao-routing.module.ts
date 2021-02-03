import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FormularioComponent } from 'src/app/modulos/pre-inscricao/componentes/formulario/formulario.component';

import { ListagemInscricaoComponent } from './componentes/listagem-inscricao/listagem-inscricao.component';
import { LoginTesteComponent } from './componentes/login-teste/login-teste.component';


const routes: Routes = [
	{
		path:'',
		component: ListagemInscricaoComponent
	},
	{
		path:'form',
		component: FormularioComponent
	},
	{
		path:"login",
		component: LoginTesteComponent
	}
];

@NgModule({
	imports: [RouterModule.forChild(routes)],
	exports: [RouterModule]
})
export class PreInscricaoRoutingModule { }
