import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Chamado } from 'src/app/model/chamado.model';
import { AuthService } from 'src/app/service/auth.service';
import { TecnicoService } from 'src/app/service/tecnico.service';

@Component({
  selector: 'app-buscachamados',
  templateUrl: './buscachamados.component.html',
  styleUrls: ['./buscachamados.component.css']
})
export class BuscachamadosComponent implements OnInit {

  public displayedColumns: string[] = ['id', 'descricao', 'status','actions'];
  public dataSource: Chamado[] = [];
  public idSelecionado: string = '';
  public idTecnico: string = '';

  constructor(private tecnico: TecnicoService, private cdr: ChangeDetectorRef, private auth: AuthService
  , private snack: MatSnackBar) { }

  ngOnInit(): void {
    this.list();
    const tokenData = this.auth.getTokenData() as { id?: string }; 
    if (tokenData && tokenData.id) {
      this.idTecnico = tokenData.id;
    }
  }

  public list() {
    this.tecnico.list().subscribe(data => {
      this.dataSource = data.filter(x => x.statusChamado !== "FECHADO");
      this.cdr.detectChanges();
      console.log(this.dataSource);
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

  public aceitarChamado(id: string) {
    this.idSelecionado = id;
    this.tecnico.aceitarChamado(this.idSelecionado, this.idTecnico)
      .subscribe(() => {
        this.message("Chamado aceito!. Aguarde um momento")
      });
  }

  public finalizarChamado(id: string) {
    this.idSelecionado = id;
    this.tecnico.finalizarChamado(this.idSelecionado, this.idTecnico)
      .subscribe(() => {
        this.message("Chamado Finalizado!. Aguarde um momento")
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

}
