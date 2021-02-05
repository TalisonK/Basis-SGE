import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { ConfirmationService, MessageService } from 'primeng';
import { Usuario } from 'src/app/dominios/usuario';
import { UsuarioService } from '../../services/usuario.service';

@Component({
  selector: 'app-listagem',
  templateUrl: './listagem.component.html',
  styleUrls: ['./listagem.component.css']
  
})
export class ListagemComponent implements OnInit {

  exibirDialog = false;
  formularioEdicao: boolean;
  usuarios: Usuario[] = [];
  usuario = new Usuario();
  usuarioAcoes: boolean;
  @Output() usuarioRemovido = new EventEmitter<Usuario>();
  
  constructor( private servico: UsuarioService, 
    private confirmatioService: ConfirmationService, private messageService:MessageService) { }

  ngOnInit(): void {
    this.buscarUsuarios();
    this.usuarioAcoes = JSON.parse(localStorage.getItem("usuario")).id == 1?false:true;
  }

  private buscarUsuarios(){
    this.servico.getUsuarios()
    .subscribe((usuarios: Usuario[]) =>{
      this.usuarios = usuarios.filter(usuario => usuario.id !== 1);
      
    }
    )}
    showDialog(edicao = false){
      this.exibirDialog = true;
      this.formularioEdicao = edicao;
    }

    fecharDialog(usuarioSalvo: Usuario){
      console.log(usuarioSalvo);
      this.exibirDialog = false;
      this.buscarUsuarios();
    }

    dialogDeletarUsuario(id: number){
      this.confirmatioService.confirm({
        message: 'Deseja excluir o usuário? ',
        accept: () => {
          this.deletarUsuario(id);
        }
      });
    }
    
    deletarUsuario(id: number) {
      this.usuarioRemovido.emit(this.usuario);
      this.servico.deletarUsuario(id)
        .subscribe(() => {
          this.addSingle("success", "Usuario deletado", "");
          this.buscarUsuarios();
       },
       err => alert(err));
  }

  addSingle(error,sumary, detalhes) {
    this.messageService.add({severity:error, summary:sumary, detail:detalhes});
  }
} 
