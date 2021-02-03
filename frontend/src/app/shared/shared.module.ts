import { NgModule } from '@angular/core';
import { ConfirmationService } from 'primeng';
import { PRIMENG_IMPORTS } from './primeng-imports';
import { LoginComponent } from './login/login/login.component';
import { LoginService } from './login/login.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LoginTemplateComponent } from './login/login-template/login-template.component';


@NgModule({
    imports: [
        PRIMENG_IMPORTS,
        FormsModule,
        ReactiveFormsModule
    ],
    providers: [
        ConfirmationService,LoginService ],
    exports: [
        PRIMENG_IMPORTS,
    ],
    declarations: [LoginComponent, LoginTemplateComponent]
})
export class SharedModule { }
