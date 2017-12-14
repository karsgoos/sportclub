import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { ErrorService, UserService } from '../services/index';

@Component({
    moduleId: module.id.toString(),
    templateUrl: 'register.component.html'
})

export class RegisterComponent {
    model: any = {};
    loading = false;

    constructor(
        private router: Router,
        private userService: UserService,
        private errorService: ErrorService) { }

    register() {
        this.loading = true;
        console.log("Dit werkt");
        this.userService.create(this.model)
            .subscribe(
                data => {
                    this.errorService.success('Registration successful', true);
                    this.router.navigate(['/login']);
                },
                error => {
                    this.errorService.error(error);
                    this.loading = false;
                });
    }
}






