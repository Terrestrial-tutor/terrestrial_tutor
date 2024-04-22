import {Component, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {dataService} from "../services/data.service";
import {TaskService} from "../../task/services/task.service";
import {Task} from "../../../models/Task";
import {TaskSelect} from "../../../models/TaskSelect";
import {CodemirrorComponent} from "@ctrl/ngx-codemirror";
import {TutorService} from "../services/tutor.service";
import {Router} from "@angular/router";
import {Homework} from "../../../models/Homework";
import * as HomeworkActions from "../storage/homework.actions";
import {Store} from "@ngrx/store";
import * as HomeworkSelectors from "../storage/homework.selectors";
import {map, Subscription} from "rxjs";
import {TutorDataService} from "../storage/tutor.data.service";

@Component({
  selector: 'app-task-choise',
  templateUrl: './task-choice.component.html',
  styleUrls: ['./task-choice.component.css']
})
export class TaskChoiceComponent implements OnInit {

  @ViewChild('codemirrorComponent') codemirror: CodemirrorComponent | undefined;

  constructor(private dataService : dataService,
              private taskService : TaskService,
              private tutorService : TutorService,
              private router: Router,
              private store: Store,
              private tutorDataService: TutorDataService,) { }

  allTasks: TaskSelect[] = [];
  subject: any;
  pageLoaded: boolean = false;
  homeworkLoaded: boolean = false;
  isCollapsed: boolean[] = [];
  homework: Homework | null = null;
  subscriptions$: Subscription[] = [];

  ngOnInit(): void {
    if (this.tutorDataService.getHomework()) {
      this.homework = this.tutorDataService.getHomework();
      this.subject = this.homework?.subject;
      this.setTasks();
    } else {
      let homework: number = Number(sessionStorage.getItem("homeworkId"));
      this.tutorService.getHomework(homework).subscribe(homework => {
        this.homework = homework;
        this.subject = this.homework?.subject;
        this.setTasks();
      });
    }
  }

  setTasks() {
    this.taskService.getTasksBySubject(this.subject).pipe(map(tasks => tasks)).subscribe(tasks => {
      for (let i = 0; i < tasks.length; i++) {
        if (this.homework != null && this.homework.tasks.some(task => task.id == tasks[i].id)) {
          this.allTasks.push(new TaskSelect(tasks[i], true));
        } else {
          this.allTasks.push(new TaskSelect(tasks[i]));
        }
        this.isCollapsed.push(true);
      }
      this.pageLoaded = true;
    });
  }

  checkImage(file: string): boolean {
    return file.endsWith('.jpg') || file.endsWith('.png') || file.endsWith('.jpeg');
  }

  codemirrorInit() {
    if (this.codemirror != undefined) {
      this.codemirror.codeMirror?.refresh();
    }
  }

  getSelectedTasks() {
    let selectedTasksIds = [];
    for (let task of this.allTasks) {
      if (task.isSelected) {
        selectedTasksIds.push(task.task);
      }
    }
    return selectedTasksIds;
  }

  submit() {
    if (this.homework != null) {
      let currentTasks = this.getSelectedTasks();
      let newTasksList: { [key: number]: string; } = {};
      let tasks = []
      for (let i = 0; i < currentTasks.length; i++) {
        tasks.push(currentTasks[i]);
        if (!this.homework.tasksCheckingTypes[currentTasks[i].id]) {
          newTasksList[currentTasks[i].id] = 'AUTO';
        } else {
          newTasksList[currentTasks[i].id] = this.homework.tasksCheckingTypes[currentTasks[i].id];
        }
      }
      this.homework.tasksCheckingTypes = newTasksList;
      this.homework.tasks = tasks;
      this.tutorDataService.setHomework(this.homework);
      this.pageLoaded = false;
      this.tutorService.saveHomework(this.homework).subscribe(homework => {
        this.pageLoaded = true;
        this.router.navigate(['/tutor/constructor']);
      });
    }
  }
}
