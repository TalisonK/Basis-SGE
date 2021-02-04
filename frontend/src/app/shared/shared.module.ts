import { NgModule } from '@angular/core';
import { ConfirmationService } from 'primeng';
import { PRIMENG_IMPORTS } from './primeng-imports';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';


@NgModule({
    imports: [
        PRIMENG_IMPORTS,
        FormsModule,
        ReactiveFormsModule
    ],
    providers: [
        ConfirmationService],
    exports: [
        PRIMENG_IMPORTS,
    ]
})
export class SharedModule { }
