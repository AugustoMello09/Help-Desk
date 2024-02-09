import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

import { Observable } from 'rxjs';
import { ClienteLogin } from '../model/clientelogin.model';

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
}
