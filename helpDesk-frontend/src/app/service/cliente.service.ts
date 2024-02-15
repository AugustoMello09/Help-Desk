import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

import { Observable } from 'rxjs';
import { ClienteLogin } from '../model/clientelogin.model';
import { AbrirChamado } from '../model/abrirchamado.model';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  baseUrl: string = environment.baseUrl;

  constructor(private http: HttpClient) { }

  public create(cliente: ClienteLogin): Observable<ClienteLogin> {
    const url = `${this.baseUrl}/cliente`
    return this.http.post<ClienteLogin>(url, cliente);
  }

  public abrirChamdo(descricao: AbrirChamado, id : any): Observable<AbrirChamado> {
    const url = `${this.baseUrl}/cliente/fazerChamado/${id}`
    return this.http.post<AbrirChamado>(url, descricao);
  }
}
