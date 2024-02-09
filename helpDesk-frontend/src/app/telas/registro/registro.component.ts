import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialog, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';

import { AuthService } from 'src/app/service/auth.service';
import { LoginComponent } from '../login/login.component';
import { ClienteService } from 'src/app/service/cliente.service';
import { ClienteLogin } from 'src/app/model/clientelogin.model';


@Component({
  selector: 'app-registro',
  templateUrl: './registro.component.html',
  styleUrls: ['./registro.component.css']
})
export class RegistroComponent implements OnInit {
  
  usuario: ClienteLogin = {
    id: '',
    nome: '',
    email: '',
    senha: '',
    cargos: [
      { id: 1 } 
    ]
  }

  constructor(private service: ClienteService,  private dialogRef: MatDialogRef<RegistroComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any, private dialog: MatDialog, private auth: AuthService, private snack: MatSnackBar) { }

  ngOnInit(): void {
  }

  public create() {
    this.service.create(this.usuario).subscribe(() => {
      this.auth.tentarLogar(this.usuario.email, this.usuario.senha).subscribe(res => {
        const token_access = JSON.stringify(res);
        localStorage.setItem('token_access', token_access);
        this.message("Conta criada com Sucesso!");
        this.dialogRef.close(false);
        window.location.reload();
      })
    }, err => {
      for (const error of err.error.errors) {
        this.addMessageError(error.fieldName, error.message); 
      }
    })
  }

  public login() {
    this.dialog.open(LoginComponent, {
      disableClose: true
    }).afterClosed().subscribe(() => {
      console.log('modal editar fechada');
      this.dialogRef.close(false);
      window.location.reload();
    });
  }

  public message(msg: string) {
    this.snack
      .open(`${msg}`, 'OK', {
        horizontalPosition: 'center',
        verticalPosition: 'bottom',
        duration: 8000
    })
  }

  public addMessageError(fieldName: string, errorMessage: string) {
    this.message(`${fieldName}: ${errorMessage}`);
  }


}
