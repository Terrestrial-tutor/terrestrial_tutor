import { __decorate } from "tslib";
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from "./modules/auth/login/login.component";
import { RegistrationComponent } from "./modules/auth/registration/registration.component";
import { PupilComponent } from "./modules/pupil/pupil.component";
import { authInterceptorProviders } from "./modules/auth/helper/auth-interceptor.service";
import { authErrorInterceptorProviders } from "./modules/auth/helper/error-interceptor.service";
import { AdminComponent } from "./modules/admin/admin.component";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { HttpClientModule } from "@angular/common/http";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { SubjectsComponent } from './modules/subjects/subjects.component';
import { NgbAlertModule, NgbModule } from "@ng-bootstrap/ng-bootstrap";
import { SupportComponent } from './modules/support/support.component';
import { TaskComponent } from './modules/task/task.component';
import { CodemirrorModule } from "@ctrl/ngx-codemirror";
import { TutorComponent } from './modules/tutor/tutor.component';
import { HwConstructorComponent } from './modules/tutor/hw-constructor/hw-constructor.component';
// import { TaskChoiceComponent } from './modules/tutor/task-choise/task-choice.component';
import { BrowseNotificationsComponent } from './modules/modals/browse-notifications.component';
import { PupilsAddHomeworkComponent } from './modules/tutor/pupils-add-homework/pupils-add-homework.component';
let AppModule = class AppModule {
};
AppModule = __decorate([
    NgModule({
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
            TaskChoiceComponent,
            BrowseNotificationsComponent,
            PupilsAddHomeworkComponent,
        ],
        imports: [
            BrowserModule,
            AppRoutingModule,
            BrowserAnimationsModule,
            ReactiveFormsModule,
            HttpClientModule,
            FormsModule,
            NgbAlertModule,
            NgbModule,
            CodemirrorModule
        ],
        providers: [authInterceptorProviders, authErrorInterceptorProviders],
        bootstrap: [AppComponent]
    })
], AppModule);
export { AppModule };
//# sourceMappingURL=app.module.js.map
