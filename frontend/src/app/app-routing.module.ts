import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from "./modules/auth/login/login.component";
import {AuthGuardService} from "./modules/auth/helper/auth-guard.service";
import {RegistrationComponent} from "./modules/auth/registration/registration.component";
import {AdminComponent} from "./modules/admin/admin.component";
import {PupilComponent} from "./modules/pupil/pupil.component";

const routes: Routes = [
  {path: 'login', component: LoginComponent, canActivate: [AuthGuardService]},
  {path: 'registration', component: RegistrationComponent, canActivate: [AuthGuardService]},
  {path: 'admin', component: AdminComponent, canActivate: [AuthGuardService]},
  {path: 'pupil', component: PupilComponent, canActivate: [AuthGuardService]},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
