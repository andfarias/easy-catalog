import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { Observable, of, switchMap, take } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private router: Router, private authService: AuthService) { }

  canActivate(): Observable<boolean> {
    return this.authService.validateToken().pipe(
      switchMap(isValid => {
        console.log("isValid ", isValid)
        if (isValid) {
          return of(true);
        } else {
          this.router.navigate(['/login']);
          return of(false);
        }
      }),
      take(1)
    );
  }
}
