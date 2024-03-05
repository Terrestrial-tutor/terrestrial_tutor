import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {EnvironmentService} from "../../environments/environment.service";


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient,
              private apiService: EnvironmentService) {
  }

  private AUTH_API = this.apiService.apiUrl + 'auth/';

  public login(user: { username: any; password: any; }): Observable<any> {
    console.log("ok");
    return this.http.post(this.AUTH_API + 'login', {
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
    return this.http.post(this.AUTH_API + 'registration', {
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
