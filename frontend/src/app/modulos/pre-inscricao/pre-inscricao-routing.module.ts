import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FormularioComponent } from 'src/app/modulos/pre-inscricao/componentes/formulario/formulario.component';
import { ListagemComponent } from '../usuario/componentes/listagem/listagem.component';

const routes: Routes = [
	{
		path:'',
		component: ListagemComponent
	},
	{
		path:'form',
		component: FormularioComponent
	}
];

@NgModule({
	imports: [RouterModule.forChild(routes)],
	exports: [RouterModule]
})
export class PreInscricaoRoutingModule { }
