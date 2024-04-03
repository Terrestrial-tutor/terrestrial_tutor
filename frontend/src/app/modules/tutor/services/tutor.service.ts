import { Injectable } from '@angular/core';
import {HttpClient, HttpRequest} from "@angular/common/http";
import {EnvironmentService} from "../../../../environments/environment.service";
import {Observable} from "rxjs";
import {Homework} from "../../../models/Homework";
import {RequestHomework} from "../../../models/RequestHomework";

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

  addHomework(homework: Homework): Observable<any> {
    let homeworkRequest = new RequestHomework(homework);
    console.log(homeworkRequest);
    return this.http.post(this.HOMEWORK_API + 'add', homeworkRequest);
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

  addHomeworkTasks(taskIds: number[], HWId: any): Observable<any> {
    return this.http.post(this.HOMEWORK_API + 'add/tasks/' + HWId, taskIds);
  }

}
