import { NgModule } from '@angular/core';
import { ConfirmationService } from 'primeng';
import { PRIMENG_IMPORTS } from './primeng-imports';
import { LoginComponent } from './login/login/login.component';
import { LoginService } from './login/login.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

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
    declarations: [LoginComponent]
})
export class SharedModule { }
