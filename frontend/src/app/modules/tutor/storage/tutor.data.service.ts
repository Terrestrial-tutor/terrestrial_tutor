import { Injectable } from '@angular/core';
import {Homework} from "../../../models/Homework";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class TutorDataService {

  constructor() { }

  public homework: Homework|null = null;

  getHomework(): Homework|null {
    return this.homework;
  }

  setHomework(homework: Homework|null = null) {
    this.homework = homework;
  }
}
