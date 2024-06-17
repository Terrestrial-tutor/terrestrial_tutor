import { Component, OnInit } from '@angular/core';
import {TokenStorageService} from "../../security/token-storage.service";
import {Router} from "@angular/router";
import {TaskService} from "../task/services/task.service";
import {Task} from "../../models/Task";
import {SupportDataService} from "./services/support.data.service";

@Component({
  selector: 'app-support',
  templateUrl: './support.component.html',
  styleUrls: ['./support.component.css']
})
export class SupportComponent implements OnInit {

  tasks: Task[] = [];
  tasksUpload: boolean = false;

  constructor(private tokenService: TokenStorageService,
              private router: Router,
              private supportDataService: SupportDataService,
              private taskService: TaskService,) {}

  ngOnInit(): void {
    sessionStorage.clear();
    this.supportDataService.setTask(null);
    this.taskService.getAllTasks().subscribe(tasks => {
      this.tasks = tasks;
      this.tasksUpload = true;
    });
  }

  addTask(task: Task | null = null) {
    if (task) {
      this.supportDataService.setTask(task);
      sessionStorage.setItem('taskId', JSON.stringify(task.id));
    }
    this.router.navigate(['support/task/add']);
  }

  protected readonly onsubmit = onsubmit;
}
