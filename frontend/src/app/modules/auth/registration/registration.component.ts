import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {AuthService} from "../../../security/auth.service";
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-registration',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    RouterLink
  ],
  templateUrl: './registration.component.html',
  styleUrl: './registration.component.css'
})
export class RegistrationComponent implements OnInit{
  // @ts-ignore
  public registrationForm: FormGroup;

  constructor(
    private authService: AuthService,
    private fb: FormBuilder) {
  }

  ngOnInit(): void {
    this.registrationForm = this.createRegisterForm();
  }

  private createRegisterForm() {
    return this.fb.group({
      name: ['', Validators.compose([Validators.required])],
      surname: ['', Validators.compose([Validators.required])],
      patronymic: ['', Validators.compose([Validators.required])],
      email: ['', Validators.compose([Validators.required, Validators.email])],
      password: ['', Validators.compose([Validators.required])],
      confirmPassword: ['', Validators.compose([Validators.required])],
      role: [''],
    })
  }

  submit(): void {
    console.log(this.registrationForm.value);
    this.authService.register(this.registrationForm.value).subscribe();
  }
}
