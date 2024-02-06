import { ApplicationConfig } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import {authErrorInterceptorProviders} from "./modules/auth/helper/error-interceptor.service";
import {authInterceptorProviders} from "./modules/auth/helper/auth-interceptor.service";
import {HttpClient, HttpClientModule, HttpHandler, provideHttpClient} from "@angular/common/http";

export const appConfig: ApplicationConfig = {
  providers: [provideRouter(routes), authInterceptorProviders, authErrorInterceptorProviders, provideHttpClient()]
};
