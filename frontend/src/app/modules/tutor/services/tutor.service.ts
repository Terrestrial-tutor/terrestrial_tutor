import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {EnvironmentService} from "../../../../environments/environment.service";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class TutorService {

  constructor(private http: HttpClient,
              private apiService: EnvironmentService) { }

  private TUTOR_API = this.apiService.apiUrl + 'tutor/';

  getTutorSubjects(): Observable<any> {
    return this.http.get(this.TUTOR_API + 'subjects');
  }

}
