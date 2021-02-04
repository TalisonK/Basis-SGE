import { Component, OnInit } from '@angular/core';
import { ConfirmationService } from 'primeng';
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
  
  constructor( private servico: UsuarioService, 
    private confirmatioService: ConfirmationService) { }

  ngOnInit(): void {
    this.buscarUsuarios();
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

    showDialogEditar(id: number){
      this.servico.buscarUsuarioPorId(id).subscribe(usuario => {
        this.usuario = usuario;
        console.log(this.usuario);
        
        this.showDialog(true);
      });
     
    }

    showDialogSalvar(){
      this.usuario = new Usuario();
      this.showDialog();
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
      this.servico.deletarUsuario(id)
        .subscribe(() => {
         alert('Usuário deletado');
          this.buscarUsuarios();
       },
       err => alert(err));
  }
} 
