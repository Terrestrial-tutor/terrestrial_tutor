import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../../../environments/environment";
import {ConstantsComponent} from "../../../constants/constants.component";

const CHECK_API = ConstantsComponent.MAIN_API_PATH + 'check/';
const ADMIN_API = ConstantsComponent.MAIN_API_PATH + 'admin/';
const TUTOR_API = ConstantsComponent.MAIN_API_PATH + 'tutor/';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient) { }

  getAllChecks(): Observable<any>{
    return this.http.get(CHECK_API + 'all');
  }

  deleteCheck(id: number): Observable<any>{
    return this.http.delete(CHECK_API + `close/${id}`);
  }

  findTutorsBySubject(subject: string | undefined): Observable<any> {
    return this.http.get(ADMIN_API + `subject/${subject}/find/tutors`);
  }

  getTutorPupilsBySubject(subject: string | undefined, id: number | undefined): Observable<any> {
    return this.http.get(TUTOR_API + `find/pupils/${subject}/${id}`);
  }

  findPupilsWithoutSubject(subject: string | undefined): Observable<any> {
    return this.http.get(ADMIN_API + `find/pupils/new/${subject}`);
  }

  addPupilsForTutor(pupilsIds: number[] | undefined, id: number | undefined): Observable<any> {
    console.log("ok")
    return this.http.post(ADMIN_API + `tutor/${id}/add/pupils`, pupilsIds);
  }

}
