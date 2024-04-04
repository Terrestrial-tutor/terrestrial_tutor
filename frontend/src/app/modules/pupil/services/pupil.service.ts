import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {EnvironmentService} from "../../../../environments/environment.service";


@Injectable({
  providedIn: 'root'
})
export class PupilService {

  constructor(private http: HttpClient,
              private apiService: EnvironmentService) { }

  private PUPIL_API = this.apiService.apiUrl + 'pupil/';

  getCurrentUser(): Observable<any> {
    return this.http.get(this.PUPIL_API);
  }

  addSubjects(subject: string | undefined, ids: number[] | undefined): Observable<any> {
    return this.http.post(this.PUPIL_API + `add/subjects/`, {subject, ids});
  }

  getAllPupils(): Observable<any> {
    return this.http.get(this.PUPIL_API + 'all');
  }

}
