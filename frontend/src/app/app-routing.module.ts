import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { EventoModule } from './modulos/evento/evento.module';
import { UsuarioModule } from './modulos/usuario/usuario.module';
import { PerguntaModule } from './modulos/pergunta/pergunta.module';
import { PreInscricaoModule } from './modulos/pre-inscricao/pre-inscricao.module';

const routes: Routes = [
	{ 
   path: 'eventos', 
   loadChildren: ()=>EventoModule
  },
  {
    path: 'pergunta',
    loadChildren: () => PerguntaModule
  },
	{
	path:'usuarios',
	loadChildren: () => UsuarioModule
  },
  {
    path:'inscricao',
    loadChildren: () => PreInscricaoModule
  }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
