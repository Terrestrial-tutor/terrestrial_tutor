import { Component } from '@angular/core';
import {RouterLink, RouterOutlet} from '@angular/router';
import {authErrorInterceptorProviders} from "./modules/auth/helper/error-interceptor.service";
import {authInterceptorProviders} from "./modules/auth/helper/auth-interceptor.service";
import {HttpClientModule} from '@angular/common/http';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterLink, HttpClientModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
  providers: [authInterceptorProviders, authErrorInterceptorProviders, HttpClientModule]
})
export class AppComponent {
  title = 'frontend';
}
