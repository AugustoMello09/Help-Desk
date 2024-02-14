import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-pessoa',
  templateUrl: './pessoa.component.html',
  styleUrls: ['./pessoa.component.css']
})
export class PessoaComponent implements OnInit {

  tokenData: any;

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
    this.tokenData = this.authService.getTokenData();
  }

  sair(): void {
    this.authService.sairDaConta();
    window.location.reload();
  }

}
