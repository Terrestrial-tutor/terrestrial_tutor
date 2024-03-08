import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from "./modules/auth/login/login.component";
import {AuthGuardService} from "./modules/auth/helper/auth-guard.service";
import {RegistrationComponent} from "./modules/auth/registration/registration.component";
import {AdminComponent} from "./modules/admin/admin.component";
import {PupilComponent} from "./modules/pupil/pupil.component";
import {SupportComponent} from "./modules/support/support.component";
import {TaskComponent} from "./modules/task/task.component";

const routes: Routes = [
  {path: 'login', component: LoginComponent, canActivate: [AuthGuardService]},
  {path: 'registration', component: RegistrationComponent, canActivate: [AuthGuardService]},
  {path: 'admin', component: AdminComponent, canActivate: [AuthGuardService]},
  {path: 'pupil', component: PupilComponent, canActivate: [AuthGuardService]},
  {path: 'support', component: SupportComponent, canActivate: [AuthGuardService]},
  {path: 'support/task/add', component: TaskComponent, canActivate: [AuthGuardService]},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
