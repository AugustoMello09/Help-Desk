import { Component } from '@angular/core';
import { AuthService } from './service/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'helpDesk-frontend';
  public events: string[] = [];
  public opened: boolean = false;

  userRoles: string[] = [];

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
    this.userRoles = this.authService.hasAdminRole();
    localStorage.getItem('token_access');
  }

  hasRole(role: string): boolean {
    return this.userRoles.includes(role);
  }
}
