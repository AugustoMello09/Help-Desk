import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { Chamado } from 'src/app/model/chamado.model';
import { TecnicoService } from 'src/app/service/tecnico.service';

@Component({
  selector: 'app-fechado',
  templateUrl: './fechado.component.html',
  styleUrls: ['./fechado.component.css']
})
export class FechadoComponent implements OnInit {

  public displayedColumns: string[] = ['id', 'descricao', 'status', 'actions'];
  public dataSource: Chamado[] =  [];

  constructor(private tecnico: TecnicoService, private cdr: ChangeDetectorRef) { }

  ngOnInit(): void {
    this.list();
  }

  public list() {
    this.tecnico.list().subscribe(data => {
      this.dataSource = data.filter(x => x.statusChamado === "FECHADO");
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

}
