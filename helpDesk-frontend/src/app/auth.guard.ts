import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { LoginComponent } from './telas/login/login.component';
import { AuthService } from './service/auth.service';
import { MatDialog } from '@angular/material/dialog';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
 
  constructor(
    private authService: AuthService,
    private dialog: MatDialog,
    private router: Router
  ) { }
  
  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): boolean {
    if (this.authService.isAuthenticated()) {
      return true;
    } else {
      this.router.navigate(['']);
      this.dialog.open(LoginComponent, {
        disableClose: true
      }).afterClosed().subscribe(() => {
        console.log('modal editar fechada');
      });
      return false;
    }

  }
  
}
