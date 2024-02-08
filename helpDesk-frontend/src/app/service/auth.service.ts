import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { environment } from 'src/environments/environment';
import { jwtDecode }  from 'jwt-decode';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  baseUrl: string = environment.baseUrl;
  loginUrl: string = environment.baseUrl + '/auth/login';
  jwtHelper: JwtHelperService = new JwtHelperService();

  constructor(private http: HttpClient) { }

  tentarLogar(email: string, senha: string): Observable<any> {
    const data = {
      email: email,
      senha: senha
    };
    const headers = {
      'Content-Type': 'application/json'
    };
    return this.http.post(this.loginUrl, data, { headers });
  }

 obterToken() {
  const tokenString = localStorage.getItem('token_access');
  if (tokenString) {
    const token = JSON.parse(tokenString).token;
    return token;
  }
  return null;
}

  isAuthenticated(): boolean {
    const token = this.obterToken();
    if (token) {
      const expired = this.jwtHelper.isTokenExpired(token)
      return !expired;
    }
    return false;
  }

  hasAdminRole(): string[] {
    const token = this.obterToken();
    if (token) {
      const decodedToken: any = jwtDecode(token);
      if (decodedToken.roles && typeof decodedToken.roles === 'string') {
        const userRoles: string[] = decodedToken.roles.split(',');
        return userRoles.map(role => role.trim());
      }
    }
    return [];
  }

  sairDaConta() {
    localStorage.removeItem('token_access');
    localStorage.removeItem('userId');
  }

  getTokenData() {
    const token =  this.obterToken();
    if (token) {
      const decodedToken = jwtDecode(token);
      return decodedToken;
    }
    return null;
  }

}
