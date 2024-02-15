import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';

import { MaterialModule } from './material.model';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule } from '@angular/forms';

import { HeaderComponent } from './template/header/header.component';
import { FooterComponent } from './template/footer/footer.component';
import { HomeComponent } from './home/home.component';
import { DialogComponent } from './template/dialog/dialog.component';
import { LoginComponent } from './telas/login/login.component';
import { RegistroComponent } from './telas/registro/registro.component';
import { AuthService } from './service/auth.service';
import { AuthInterceptor } from './auth.interceptor';
import { PessoaComponent } from './telas/pessoa/pessoa.component';
import { ChamadosComponent } from './telas/chamados/chamados.component';
import { AbrirchamadoComponent } from './telas/chamados/abrirchamado/abrirchamado.component';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    HomeComponent,
    DialogComponent,
    LoginComponent,
    RegistroComponent,
    PessoaComponent,
    ChamadosComponent,
    AbrirchamadoComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [AuthService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass:  AuthInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
