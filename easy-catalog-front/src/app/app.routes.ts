import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { ProductListComponent } from './components/product-list/product-list.component';
import { inject } from '@angular/core';
import { AuthService } from './services/auth.service';
import { map } from 'rxjs';

export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  {
    path: 'products',
    component: ProductListComponent,
    canActivate: [() => {
      return inject(AuthService).isAuthenticated().pipe(
        map(isAuth => isAuth || '/login')
      );
    }]
  },
  { path: '', redirectTo: '/login', pathMatch: 'full' }
];