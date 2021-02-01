import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DiarioErrosComponent } from './components/diario-erros/diario-erros.component';
import { LoginSuccessComponent } from '@nuvem/angular-base';
import { EventoModule } from './modulos/evento/evento.module';
import { UsuarioModule } from './modulos/usuario/usuario.module';
import { PerguntaModule } from './modulos/pergunta/pergunta.module';
import { InscricaoService } from './modulos/pre-inscricao/services/inscricao-service.service';
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
  },
    { path: 'diario-erros', component: DiarioErrosComponent, data: { breadcrumb: 'Diário de Erros'} },
    { path: 'login-success', component: LoginSuccessComponent },
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
