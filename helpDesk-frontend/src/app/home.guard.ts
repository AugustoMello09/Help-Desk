import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from './service/auth.service';
import { MatDialog } from '@angular/material/dialog';
import { RegistroComponent } from './telas/registro/registro.component';

@Injectable({
  providedIn: 'root'
})
export class HomeGuard implements CanActivate {
  
  constructor(
    private authService: AuthService,
    private dialog: MatDialog
  ) { }
  
  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): boolean {
    if (this.authService.isAuthenticated()) {
      return true;
    } else {
      this.dialog.open(RegistroComponent, {
        disableClose: true
      }).afterClosed().subscribe(() => {
        console.log('modal editar fechada');
      });
      return false;
    }

  }
  
}
