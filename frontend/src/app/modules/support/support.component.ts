import { Component, OnInit } from '@angular/core';
import {TokenStorageService} from "../../security/token-storage.service";
import {Router} from "@angular/router";
import {TaskService} from "../task/services/task.service";
import {Task} from "../../models/Task";

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
              private taskService: TaskService,) {}

  ngOnInit(): void {
    this.taskService.getAllTasks().subscribe(tasks => {
      this.tasks = tasks;
      this.tasksUpload = true;
    });
  }

  addTask() {
    this.router.navigate(['support/task/add']);
  }
}
