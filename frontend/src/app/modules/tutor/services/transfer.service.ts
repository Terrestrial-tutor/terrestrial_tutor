import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TransferService {

  private subject: any;
  //@ts-ignore
  private hwTasks: any[];

  getSubjectName(){
    return localStorage.getItem('subject');
  }

  setSubjectName(value: any){
    localStorage.setItem('subject', value);
  }

  deleteSubjectName(){
    localStorage.removeItem('subject');
  }

  getHwTasks(){
    // @ts-ignore
    return JSON.parse(localStorage.getItem('hwTasks'));
  }

  setHwTasks(hwTasks: any[]){
    localStorage.setItem("hwTasks", JSON.stringify(hwTasks));
  }

  addHwTask(task: any[]){
    let tasks = this.getHwTasks();
    tasks.push(task);
    this.setHwTasks(tasks);
  }

  deleteHwTasks(){
    localStorage.removeItem('hwTasks');
  }

  deleteHwTask(task: any){
    let tasks = this.getHwTasks();
    tasks.removeItem(task);
    this.setHwTasks(tasks);
  }
}
