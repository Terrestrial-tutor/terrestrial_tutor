import { Routes } from '@angular/router';
import {LoginComponent} from "./modules/auth/login/login.component";
import {RegistrationComponent} from "./modules/auth/registration/registration.component";
import {AdminComponent} from "./modules/admin/admin.component";

export const routes: Routes = [
    {path: 'login', component: LoginComponent},
    {path: 'registration', component: RegistrationComponent},
    {path: 'admin', component: AdminComponent},
];
