import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { HomeComponent } from './home/home.component';
import { HomeGuard } from './home.guard';
import { PessoaComponent } from './telas/pessoa/pessoa.component';
import { AuthGuard } from './auth.guard';
import { ChamadosComponent } from './telas/chamados/chamados.component';

const routes: Routes = [
  {path: '', component:HomeComponent, canActivate : [HomeGuard] },
  { path: 'conta', component: PessoaComponent, canActivate: [AuthGuard] },
   {path: 'chamado', component:ChamadosComponent, canActivate : [AuthGuard] },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
