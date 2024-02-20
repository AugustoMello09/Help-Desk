import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { AbrirChamado } from 'src/app/model/abrirchamado.model';
import { UsuarioId } from 'src/app/model/usuarioId.model';
import { AuthService } from 'src/app/service/auth.service';
import { ClienteService } from 'src/app/service/cliente.service';

@Component({
  selector: 'app-abrirchamado',
  templateUrl: './abrirchamado.component.html',
  styleUrls: ['./abrirchamado.component.css']
})
export class AbrirchamadoComponent implements OnInit {

  constructor(private dialogRef: MatDialogRef<AbrirchamadoComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any, private service: ClienteService, private auth: AuthService) { }

    clienteId: UsuarioId = {
      id: ''
    }
    
    chamado: AbrirChamado  = {
    descricao: ''
    }
  
  ngOnInit(): void {
    const tokenData = this.auth.getTokenData() as { id?: string }; 
      if (tokenData && tokenData.id) {
        this.clienteId.id = tokenData.id;
      }
  }

  cancel() {
    this.dialogRef.close(false);
  }

  save() {
    this.service.abrirChamdo(this.chamado, this.clienteId.id).subscribe(data => {
      this.chamado = data;
      window.location.reload();
      this.dialogRef.close(true);
    })
  }

}
