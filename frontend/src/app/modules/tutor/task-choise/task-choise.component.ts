import { Component, OnInit } from '@angular/core';
import {TransferService} from "../services/transfer.service";
import {TaskService} from "../../task/services/task.service";
import {Task} from "../../../models/Task";

@Component({
  selector: 'app-task-choise',
  templateUrl: './task-choise.component.html',
  styleUrls: ['./task-choise.component.css']
})
export class TaskChoiseComponent implements OnInit {

  constructor(private transfer : TransferService,
              private taskService : TaskService,) { }

  //@ts-ignore
  allTasks: Task[];
  subject: any;
  tasksUpload: boolean = false;
  isCollapsed: boolean[] = [];

  ngOnInit(): void {
    this.subject = this.transfer.getSubjectName();
    this.taskService.getTasksBySubject(this.subject).subscribe(tasks => {
      this.allTasks = tasks;
      for (let i = 0; i < tasks.length; i++) {
        this.isCollapsed.push(true);
      }
      this.transfer.setHwTasks(tasks);
      this.tasksUpload = true;
    });
  }

  checkImage(file: string): boolean {
    return file.endsWith('.jpg') || file.endsWith('.png') || file.endsWith('.jpeg');
  }

}
