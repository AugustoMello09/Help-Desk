import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Chamado } from 'src/app/model/chamado.model';
import { AuthService } from 'src/app/service/auth.service';
import { TecnicoService } from 'src/app/service/tecnico.service';
import { AbrirchamadoComponent } from './abrirchamado/abrirchamado.component';

@Component({
  selector: 'app-chamados',
  templateUrl: './chamados.component.html',
  styleUrls: ['./chamados.component.css']
})
export class ChamadosComponent implements OnInit {

  chamados: Chamado[] = [];

  constructor(private tecnico: TecnicoService,
    private dialog: MatDialog,
    private auth: AuthService) { }

  ngOnInit(): void {
    this.list();
  }

  public list() {
    let nome: string = '';
    const tokenData = this.auth.getTokenData() as { nome: string };
    if (tokenData && tokenData.nome) { 
      nome = tokenData.nome;
      this.tecnico.listaChamados(nome).subscribe(res => {
        this.chamados = res;
        console.log(this.chamados)
      })
    }
  }

  public abrirChamado() {
    this.dialog.open(AbrirchamadoComponent, {
      disableClose: true
    }).afterClosed().subscribe(() => {
      console.log('modal editar fechada');
   })
  }

  public prioridade(x: any) {
    if (x === "ABERTO") {
     return "ABERTO"
    } else if (x === "ANDAMENTO") {
      return "ANDAMENTO"
    } else {
      return "FINALIZADO"
    }
  }

}
