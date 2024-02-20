import { Injectable } from '@angular/core';
import {ConstantsComponent} from "../../../constants/constants.component";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

const PUPIL_API = ConstantsComponent.MAIN_API_PATH + 'pupil/';

@Injectable({
  providedIn: 'root'
})
export class PupilService {

  constructor(private http: HttpClient) { }

  getCurrentUser(): Observable<any> {
    return this.http.get(PUPIL_API);
  }

  addSubjects(subject: string | undefined, ids: number[] | undefined): Observable<any> {
    return this.http.post(PUPIL_API + `add/subjects/`, {subject, ids});
  }



}
