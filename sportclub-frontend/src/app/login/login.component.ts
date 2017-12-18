import {Component, OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {AuthenticationService} from './services';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers:  [AuthenticationService]
})
export class LoginComponent implements OnInit {
  model: any = {};
  loading = false;
  returnUrl: string;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private authService: AuthenticationService) {
  }

  ngOnInit() {
  }

  login() {
    this.loading = true;
    this.authService.login(this.model.email, this.model.password)
      .then(
        data => {
          this.router.navigate(['/points']);
        },
        error => {
          this.loading = false;
        });
  }

}
