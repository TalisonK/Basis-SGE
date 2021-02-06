import { Component, EventEmitter, Output } from '@angular/core';
import { AppComponent } from '../../app.component';
import { Authentication, User } from '@nuvem/angular-base';
import { Router } from '@angular/router';
import { Usuario } from 'src/app/dominios/usuario';

@Component({
    selector: 'app-topbar',
    templateUrl: './app.topbar.component.html'
})
export class AppTopbarComponent {

    @Output() sairEvento = new EventEmitter();

    usuario:String = "";

    constructor(
        public app: AppComponent,
        public router:Router) {
    }

    ngOnInit(): void {
        this.usuario = JSON.parse(localStorage.getItem("usuario")).nome;
    }

    sair(){
        this.sairEvento.emit(null);
        this.router.navigate(["/"]);
    }
}
