import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FormularioComponent } from 'src/app/modulos/pre-inscricao/formulario/formulario.component';

const routes: Routes = [
	{
		path:'',
		component: FormularioComponent
	}
];

@NgModule({
	imports: [RouterModule.forChild(routes)],
	exports: [RouterModule]
})
export class PreInscricaoRoutingModule { }
