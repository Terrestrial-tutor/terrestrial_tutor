import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ConstantsComponent} from '../constants/constants.component';

const AUTH_API = ConstantsComponent.MAIN_API_PATH + 'auth/';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  public login(user: { username: any; password: any; }): Observable<any> {
    return this.http.post(AUTH_API + 'login', {
      username: user.username,
      password: user.password
    });
  }

  public register(user: {
    name: any;
    surname: any;
    patronymic: any;
    email: any;
    password: any;
    confirmPassword: any;
    role: any;
  }): Observable<any> {
    return this.http.post(AUTH_API + 'registration', {
      name: user.name,
      surname: user.surname,
      patronymic: user.patronymic,
      email: user.email,
      password: user.password,
      confirmPassword: user.confirmPassword,
      role: user.role
    });
  }
}
