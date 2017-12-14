import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ApiService} from './api.service';
import {ApiComponent} from './api.component';

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [ApiComponent],
  providers: [ApiService]
})
export class ApiModule {
}
