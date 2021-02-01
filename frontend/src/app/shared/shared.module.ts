import { NgModule } from '@angular/core';
import { ConfirmationService } from 'primeng';
import { PRIMENG_IMPORTS } from './primeng-imports';

@NgModule({
    imports: [
        PRIMENG_IMPORTS,
    ],
    providers: [
        ConfirmationService],
    exports: [
        PRIMENG_IMPORTS,
    ]
})
export class SharedModule { }
