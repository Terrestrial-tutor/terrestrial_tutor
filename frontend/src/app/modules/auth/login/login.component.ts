import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {AuthService} from "../../../security/auth.service";
import {TokenStorageService} from "../../../security/token-storage.service";
import {Router, RouterLink} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: 'login.component.html',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    RouterLink
  ],
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  // @ts-ignore
  public loginForm: FormGroup;

  constructor(
    private authService: AuthService,
    private tokenStorage: TokenStorageService,
    private router: Router,
    private fb: FormBuilder) {
    // if (this.tokenStorage.getUser()) {
    //   this.router.navigate(['main']);
    // }
  }

  ngOnInit(): void {
    this.loginForm = this.createLoginForm();
  }

  createLoginForm(): FormGroup {
    return this.fb.group({
      username: ['', Validators.compose([Validators.required, Validators.email])],
      password: ['', Validators.compose([Validators.required])],
    });
  }

  submit(): void {
    this.authService.login({
      username: this.loginForm.value.username,
      password: this.loginForm.value.password
    }).subscribe(data => {
      console.log(data);

      this.tokenStorage.saveToken(data.token);
      this.tokenStorage.saveUser(data);
      console.log(data);
      /*this.router.navigate(['/']);
      window.location.reload();*/
    }, error => {
      console.log(error);
    });
  }

}