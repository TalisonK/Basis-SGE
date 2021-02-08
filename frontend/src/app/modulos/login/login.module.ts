import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { LoginRoutingModule } from './login-routing.module';
import { LoginTemplateComponent } from './login-template/login-template.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { LoginComponent } from './login-template/login-form/login-form.component';
import { CadastroFormComponent } from './login-template/cadastro-form/cadastro-form.component';


@NgModule({
  declarations: [LoginTemplateComponent, LoginComponent, CadastroFormComponent],
  imports: [
    CommonModule,
    LoginRoutingModule,
    SharedModule
  ], exports:[
    LoginTemplateComponent
  ]
})
export class LoginModule { }
