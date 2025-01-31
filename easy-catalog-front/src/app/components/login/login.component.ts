import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: `./login.html`,
  styleUrls: [`./login.css`]
})
export class LoginComponent {
  username = '';
  password = '';
  error = false;

  constructor(
    private authService: AuthService,
    private router: Router
  ) { }

  onSubmit() {
    this.authService.login(this.username, this.password).subscribe({
      next: () => {
        this.router.navigate(['/products']);
      },
      error: () => {
        this.error = true;
      }
    });
  }
}