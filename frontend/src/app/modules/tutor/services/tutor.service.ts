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
  private HOMEWORK_API = this.apiService.apiUrl + 'homework/';

  getTutorSubjects(): Observable<any> {
    return this.http.get(this.TUTOR_API + 'subjects');
  }

  addEmptyHomework(subject: any): Observable<any> {
    return this.http.post(this.HOMEWORK_API + 'add', subject);
  }

  deleteHomeworkById(id: any): Observable<any> {
    return this.http.delete(this.HOMEWORK_API + 'delete/' + id);
  }

  isHomeworkEmpty(id: any): Observable<any> {
    return this.http.get(this.HOMEWORK_API + 'empty/' + id);
  }

  getHomework(id: any): Observable<any> {
    return this.http.get(this.HOMEWORK_API + id);
  }

}
