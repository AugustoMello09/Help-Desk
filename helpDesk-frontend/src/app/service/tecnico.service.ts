import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Chamado } from '../model/chamado.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TecnicoService {

  baseUrl: string = environment.baseUrl;

  constructor(private http: HttpClient) { }

  public listaChamados(nome : any): Observable<Chamado[]>{
    const url = `${this.baseUrl}/cliente/meusChamados/${nome}`
    return this.http.get<Chamado[]>(url);
  }

  public list(): Observable<Chamado[]>{
    const url = `${this.baseUrl}/tecnico/listaChamados`
    return this.http.get<Chamado[]>(url);
  }

}
