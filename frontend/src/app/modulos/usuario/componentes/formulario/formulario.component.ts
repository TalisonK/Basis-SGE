import { HttpErrorResponse } from '@angular/common/http';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { MessageService } from 'primeng/api';
import { Usuario } from 'src/app/dominios/usuario';
import { UsuarioService } from '../../services/usuario.service';

@Component({
  selector: 'app-formulario',
  templateUrl: './formulario.component.html',
  styleUrls: ['./formulario.component.css']
})
export class FormularioComponent implements OnInit {

  @Input() edicao = false;
  @Input() usuario = new Usuario();
  @Output() usuarioSalvo = new EventEmitter<Usuario>();
  @Output() usuarioEditado = new EventEmitter<Usuario>();
  @Output() usuarioRemovido = new EventEmitter<Usuario>();
  formUsuario: FormGroup;


  constructor( 
    private fb: FormBuilder, 
    private usuarioService: UsuarioService,
    private messageService: MessageService,
    private route: ActivatedRoute
    ){}

  ngOnInit(): void {
    
    this.route.params.subscribe(params => {
      if (params.id){
        this.edicao = true;
        this.buscarUsuario(params.id);
      }
    });
    this.formUsuario = this.fb.group({
      nome: ['', Validators.minLength(3)],
      cpf: ['', Validators.maxLength(11)],
      email: ['', Validators.email],
      telefone: ['', Validators.maxLength(14)],
      dataNascimento: '',
    });
  }

  buscarUsuario(id: number){
    this.usuarioService.buscarUsuarioPorId(id)
    .subscribe(usuario => this.usuario = usuario);
  }
  
  fecharDialog(usuarioSalvo: Usuario) {
    this.usuarioSalvo.emit(usuarioSalvo);
    }

  editar(){
    this.usuarioEditado.emit(this.usuario);
    this.addSingle("success", "Usuário editado com sucesso", "");
    
  }
  addSingle(error,sumary, detalhes) {
    this.messageService.add({severity:error, summary:sumary, detail:detalhes});
  }
  }