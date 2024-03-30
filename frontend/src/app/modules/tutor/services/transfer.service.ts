import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TransferService {

  getSubjectName(){
    return localStorage.getItem('subject');
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

  setHwTasks(hwTasks: any[]){
    localStorage.setItem("hwTasks", JSON.stringify(hwTasks));
  }
}
