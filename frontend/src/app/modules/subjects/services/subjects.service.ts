import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ConstantsComponent} from "../../../constants/constants.component";
import {Observable} from "rxjs";

const SUBJECTS_API = ConstantsComponent.MAIN_API_PATH + 'subjects/';

@Injectable({
  providedIn: 'root'
})
export class SubjectsService {

  constructor(private http: HttpClient) { }

  getAllSubjects(): Observable<any> {
    return this.http.get(SUBJECTS_API);
  }

}
