import {Component, OnInit} from '@angular/core';
import {ApiService} from './api.service';

@Component({
  selector: 'app-api',
  template: '<div>{{ shit }}</div>'
})
export class ApiComponent implements OnInit {

  public shit: string;

  constructor(private apiService: ApiService) {
  }

  ngOnInit() {
    this.apiService.doPost('http://localhost:8080/oauth/token')
      .subscribe((data) => {
          console.log(data);
        }
      );
  }
}
