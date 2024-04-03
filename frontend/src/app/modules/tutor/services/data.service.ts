import { Injectable } from '@angular/core';
import {Homework} from "../../../models/Homework";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class  {

  private currentHomework: Homework | undefined;

  setCurrentHomework(currentHomework: Homework) {
    this.currentHomework = currentHomework;
  }

  getCurrentHomework(): Homework {
    // @ts-ignore
    return this.currentHomework;
  }

  getSubjectName(){
    return localStorage.getItem('subject');
  }
  setSubjectName(subjectName: string){
    return localStorage.setItem('subject', subjectName);
  }

  setHWId(id: any){
     localStorage.setItem('HWId', id);
  }

  getHWId(){
    return localStorage.getItem('HWId');
  }

  deleteHWId(){
    localStorage.removeItem('HWId');
  }
}
