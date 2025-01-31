import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule],
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
    if (this.authService.login(this.username, this.password)) {
      this.router.navigate(['/products']);
    } else {
      this.error = true;
    }
  }
}