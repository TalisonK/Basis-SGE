import { Component, EventEmitter, Output } from '@angular/core';
import { AppComponent } from '../../app.component';
import { Router } from '@angular/router';

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

    editarPerfil(){
        this.router.navigate(["/usuarios/editar"])
    }
}
