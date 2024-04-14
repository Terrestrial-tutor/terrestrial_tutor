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

@Component({
  selector: 'app-task-choise',
  templateUrl: './task-choice.component.html',
  styleUrls: ['./task-choice.component.css']
})
export class TaskChoiceComponent implements OnInit, OnDestroy {

  @ViewChild('codemirrorComponent') codemirror: CodemirrorComponent | undefined;

  constructor(private dataService : dataService,
              private taskService : TaskService,
              private tutorService : TutorService,
              private router: Router,
              private store: Store,) { }

  allTasks: TaskSelect[] = [];
  subject: any;
  tasksUpload: boolean = false;
  isCollapsed: boolean[] = [];
  homework: Homework | null = null;
  subscriptions$: Subscription[] = [];

  ngOnInit(): void {
    this.ngOnDestroy();
    let storageHomeworkId = sessionStorage.getItem('homeworkId');
    if (storageHomeworkId) {
      let id = parseInt(storageHomeworkId);
      this.store.dispatch(HomeworkActions.getHomeworkFromApi({id}))
    }
    this.subscriptions$.push(this.store.select(HomeworkSelectors.getHomework).pipe(map(homework =>
      homework)).subscribe(
      homework => {
        this.homework = structuredClone(homework.homework);
        this.subject = this.homework?.subject;
        this.subscriptions$.push(this.taskService.getTasksBySubject(this.subject).pipe(map(tasks => tasks)).subscribe(tasks => {
          for (let i = 0; i < tasks.length; i++) {
            if (this.homework != null && this.homework.tasks.some(task => task.id == tasks[i].id)) {
              this.allTasks.push(new TaskSelect(tasks[i], true));
            } else {
              this.allTasks.push(new TaskSelect(tasks[i]));
            }
            this.isCollapsed.push(true);
          }
          this.tasksUpload = true;
        }));
      }
    ));
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
    this.ngOnDestroy();
    if (this.homework != null) {
      let currentTasks = this.getSelectedTasks();
      let newTasksList: { [key: number]: string; } = {};
      for (let i = 0; i < currentTasks.length; i++) {
        newTasksList[currentTasks[i].id] = 'AUTO';
      }
      this.homework.tasksCheckingTypes = newTasksList;
      this.subscriptions$.push(this.tutorService.saveHomework(this.homework).subscribe(homework => {
        this.router.navigate(['/tutor/constructor']);
      }));
    }
  }

  ngOnDestroy() {
    this.subscriptions$.forEach(subscription => subscription.unsubscribe());
  }

}
