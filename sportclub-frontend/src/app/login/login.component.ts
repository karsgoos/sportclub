import {Component} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {AuthenticationService} from './services';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers:  [AuthenticationService]
})
export class LoginComponent {
  model: any = {};
  loading = false;
  returnUrl: string;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private authService: AuthenticationService) {
  }

  login() {
    this.loading = true;
    this.authService.login(this.model.email, this.model.password)
      .subscribe(
        data => {
          this.authService.fetchUser().subscribe(() => {
            this.router.navigate(['/points']);
          }, () => this.loading = false);
        }, () => this.loading = false);
  }

}
