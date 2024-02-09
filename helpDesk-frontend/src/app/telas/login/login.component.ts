import { HttpErrorResponse } from '@angular/common/http';
import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Login } from 'src/app/model/login.model';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  conta: Login = {
    email: '',
    senha: ''  
  }

  constructor(private dialogRef: MatDialogRef<LoginComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any, private authService: AuthService,
    private snack: MatSnackBar) { }

  ngOnInit(): void {
  }

  public login() {
    this.authService.tentarLogar(this.conta.email, this.conta.senha).subscribe(res => {
      const token_access = JSON.stringify(res);
      localStorage.setItem('token_access', token_access);
      this.dialogRef.close(false);
      this.message("Login feito com sucesso!");
    }, (err:  HttpErrorResponse) => {
        this.addMessageError(err);
    })
  }

  public cancel() {
    this.dialogRef.close(false);
  }

  public message(msg: string) {
    this.snack
      .open(`${msg}`, 'OK', {
        horizontalPosition: 'center',
        verticalPosition: 'bottom',
        duration: 8000
    })
  }

  public addMessageError(err: HttpErrorResponse) {
    if (err.status === 403) {
      this.message("Dados inválidos. Verifique o email e a senha.");
    } else {
      this.message('Ocorreu um erro na autenticação.');
    }
  }

}
