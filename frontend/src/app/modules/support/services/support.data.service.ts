import { Injectable } from '@angular/core';
import {Task} from "../../../models/Task";

@Injectable({
  providedIn: 'root'
})
export class SupportDataService {

  constructor() { }

  private task: Task | null = null;

  public setTask(task: Task | null) {
    this.task = task;
  }

  public getTask() {
    return this.task;
  }

}
