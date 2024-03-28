import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {EnvironmentService} from "../../../../environments/environment.service";
import {Observable} from "rxjs";
import {Task} from "../../../models/Task";

@Injectable({
  providedIn: 'root'
})
export class SupportService {

  constructor(private http: HttpClient,
              private apiService: EnvironmentService) { }

  private SUPPORT_API = this.apiService.apiUrl + 'support/';

  addTask(task: any, files: FileList): Observable<any> {
    const formData = new FormData();
    formData.append('name', task.name);
    formData.append('checking', task.checking);
    formData.append('taskText', task.taskText);
    formData.append('answerType', task.answerType);
    formData.append('answer', task.answers);
    formData.append('subject', task.subject);
    formData.append('level1', task.level1);
    formData.append('level2', task.level2);
    formData.append('table', task.table);
    console.log(files);
    for (let i = 0; i < files.length; i++) {
      formData.append('files', files[i]);
    }
    return this.http.post(this.SUPPORT_API + 'add/task', formData);
  }

}
