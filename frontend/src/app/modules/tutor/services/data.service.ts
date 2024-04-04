import { Injectable } from '@angular/core';
import {Homework} from "../../../models/Homework";
import {Observable} from "rxjs";
import {Task} from "../../../models/Task";
import {Pupil} from "../../../models/Pupil";

@Injectable({
  providedIn: 'root'
})
export class dataService {

  private homework: Homework | null = null;
  private tasks: Task[] | null = null;
  private currentPupils: Pupil[] | null = null;

  setCurrentHomework(currentHomework: any) {
    this.homework = currentHomework;
  }

  getCurrentHomework(): Homework | null {
    return this.homework;
  }

  setCurrentPupils(currentPupils: any) {
    this.currentPupils = currentPupils;
  }

  getCurrentPupils(): Pupil[] | null {
    return this.currentPupils;
  }

  setCurrentTasks(tasks: any) {
    this.tasks = tasks;
  }

  getCurrentTasks(): Task[] | null {
    return this.tasks;
  }

  getSubjectName(){
    if (this.homework) {
      return this.homework.subject;
    }
    return null;
  }

  setSubjectName(subjectName: string){
    if (this.homework) {
      this.homework.subject = subjectName;
    }
  }

  setHWId(id: any){
    if (this.homework) {
      this.homework.id = id;
    }
  }

  getHWId(){
    if (this.homework) {
      return this.homework.id
    }
    return null;
  }

  deleteHW(){
    this.homework = new Homework();
  }
}
