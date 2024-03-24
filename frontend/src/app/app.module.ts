import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {LoginComponent} from "./modules/auth/login/login.component";
import {RegistrationComponent} from "./modules/auth/registration/registration.component";
import {PupilComponent} from "./modules/pupil/pupil.component";
import {authInterceptorProviders} from "./modules/auth/helper/auth-interceptor.service";
import {authErrorInterceptorProviders} from "./modules/auth/helper/error-interceptor.service";
import {AdminComponent} from "./modules/admin/admin.component";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {MatTabsModule} from "@angular/material/tabs";
import {SubjectsComponent} from './modules/subjects/subjects.component';
import {MatOptionModule} from "@angular/material/core";
import {MatSelectModule} from "@angular/material/select";
import {MatButtonModule} from "@angular/material/button";
import {MatListModule} from "@angular/material/list";
import {MatDialogModule} from "@angular/material/dialog";
import {MatTableModule} from "@angular/material/table";
import {MatSortModule} from "@angular/material/sort";
import {MatCheckboxModule} from "@angular/material/checkbox";
import {MatInputModule} from "@angular/material/input";
import {MatProgressSpinnerModule} from "@angular/material/progress-spinner";
import {NgbAlertModule, NgbModule} from "@ng-bootstrap/ng-bootstrap";
import { SupportComponent } from './modules/support/support.component';
import { TaskComponent } from './modules/task/task.component';
import {CodemirrorModule} from "@ctrl/ngx-codemirror";
import { TutorComponent } from './modules/tutor/tutor.component';
import { HwConstructorComponent } from './modules/hw-constructor/hw-constructor.component';

@NgModule({
  declarations: [
    AppComponent,
    AdminComponent,
    LoginComponent,
    RegistrationComponent,
    PupilComponent,
    SubjectsComponent,
    SupportComponent,
    TaskComponent,
    TutorComponent,
    HwConstructorComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    HttpClientModule,
    FormsModule,
    MatTabsModule,
    MatOptionModule,
    MatSelectModule,
    MatButtonModule,
    MatListModule,
    MatDialogModule,
    MatTableModule,
    MatSortModule,
    MatCheckboxModule,
    MatInputModule,
    MatProgressSpinnerModule,
    NgbAlertModule,
    NgbModule,
    CodemirrorModule
  ],
  providers: [authInterceptorProviders, authErrorInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule {
}
