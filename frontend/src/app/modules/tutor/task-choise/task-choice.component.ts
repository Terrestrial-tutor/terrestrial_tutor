// import {Component, OnInit, ViewChild} from '@angular/core';
// import {dataService} from "../services/data.service";
// import {TaskService} from "../../task/services/task.service";
// import {Task} from "../../../models/Task";
// import {TaskSelect} from "../../../models/TaskSelect";
// import {CodemirrorComponent} from "@ctrl/ngx-codemirror";
// import {TutorService} from "../services/tutor.service";
// import {Router} from "@angular/router";
// import {Homework} from "../../../models/Homework";
//
// @Component({
//   selector: 'app-task-choise',
//   templateUrl: './task-choice.component.html',
//   styleUrls: ['./task-choice.component.css']
// })
// export class TaskChoiceComponent implements OnInit {
//
//   @ViewChild('codemirrorComponent') codemirror: CodemirrorComponent | undefined;
//
//   constructor(private dataService : dataService,
//               private taskService : TaskService,
//               private tutorService : TutorService,
//               private router: Router,) { }
//
//   allTasks: TaskSelect[] = [];
//   subject: any;
//   tasksUpload: boolean = false;
//   isCollapsed: boolean[] = [];
//   homework: Homework | null = null;
//
//   ngOnInit(): void {
//     this.homework = this.dataService.getCurrentHomework();
//     this.subject = this.dataService.getCurrentHomework()?.subject;
//     this.taskService.getTasksBySubject(this.subject).subscribe(tasks => {
//         for (let i = 0; i < tasks.length; i++) {
//           if (this.homework != null && this.homework.tasksCheckingTypes.has(tasks[i].id)) {
//             this.allTasks.push(new TaskSelect(tasks[i], true));
//           } else {
//             this.allTasks.push(new TaskSelect(tasks[i]));
//           }
//           this.isCollapsed.push(true);
//         }
//         this.tasksUpload = true;
//     });
//   }
//
//   checkImage(file: string): boolean {
//     return file.endsWith('.jpg') || file.endsWith('.png') || file.endsWith('.jpeg');
//   }
//
//   codemirrorInit() {
//     if (this.codemirror != undefined) {
//       this.codemirror.codeMirror?.refresh();
//     }
//   }
//
//   getSelectedTasks() {
//     let selectedTasksIds = [];
//     for (let task of this.allTasks) {
//       if (task.isSelected) {
//         selectedTasksIds.push(task.task);
//       }
//     }
//     return selectedTasksIds;
//   }
//
//   submit() {
//     if (this.homework != null) {
//       let currentTasks = this.getSelectedTasks();
//       let newTasksList = new Map<number, string>();
//       for (let i = 0; i < currentTasks.length; i++) {
//         newTasksList.set(currentTasks[i].id, 'AUTO')
//       }
//       this.homework.tasksCheckingTypes = newTasksList;
//       this.dataService.setCurrentTasks(currentTasks);
//       this.dataService.setCurrentHomework(this.homework);
//     }
//     this.router.navigate(['/tutor/constructor']);
//   }
//
// }
