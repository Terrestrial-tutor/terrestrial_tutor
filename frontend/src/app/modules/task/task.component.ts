import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Subject} from "../../models/Subject";
import {SubjectsService} from "../subjects/services/subjects.service";
import {SupportService} from "../support/services/support.service";
import {Task} from "../../models/Task";
import {Router} from "@angular/router";
import * as CodeMirror from 'codemirror';


@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit {

  // @ts-ignore
  taskForm: FormGroup;
  subjects: Subject[] | undefined;
  checkingTypes = ['Автоматическая', 'Ручная', 'Код'];
  code = 'function hello() {\n' +
    '  console.log(\'Hello, world!\');\n' +
    '}';
  options = {
    lineNumbers: true,
    theme: 'material',
    mode: 'text/x-c++src',
    matchBrackets: true,
    autoCloseBrackets: true,
    extraKeys: { 'Ctrl-Space': 'autocomplete' }
  };

  constructor(private fb: FormBuilder,
              private subjectsService: SubjectsService,
              private supportService: SupportService,
              private router: Router,) { }

  ngOnInit(): void {
    this.taskForm = this.createTaskForm();
    this.getAllSubjects();
  }

  private createTaskForm() {
    return this.fb.group({
      taskName: ['', Validators.compose([Validators.required])],
      taskText: ['', Validators.compose([Validators.required])],
      taskAns: ['', Validators.compose([Validators.required])],
      selectedSubject: ['Выбирете предмет', Validators.compose([(subject) => {
        if (subject.value == 'Выбирете предмет') {
          return {subjectNotSelected: false};
        } else {
          return null;
        }
      }])],
      checking: ['Выбирете тип', Validators.compose([(checking) => {
        if (checking.value == 'Выбирете тип') {
          return {checking: false};
        } else {
          return null;
        }
      }])],
      level1: ['', Validators.compose([Validators.required])],
      level2: [''],
      files: [''],
    })
  }

  getAllSubjects(): void {
    this.subjectsService.getAllSubjects().subscribe(data => {
      this.subjects = data;
    })
  }

  submit() {
    let task: Task = {
      name: this.taskForm.controls['taskName'].value,
      checking: this.checkingTypes.indexOf(this.taskForm.controls['checking'].value),
      taskText: this.taskForm.controls['taskText'].value,
      answer: this.taskForm.controls['taskAns'].value,
      answerType: '',
      subject: this.taskForm.controls['selectedSubject'].value,
      level1: this.taskForm.controls['level1'].value,
      level2: this.taskForm.controls['level2'].value,
      file: '',
      homeworks: []
    };

    this.supportService.addTask(task, this.taskForm.controls['files'].value).subscribe(data => {
      this.router.navigate(['/support']);
      console.log('Task Added');
    })
  }

  invalid(controlName: string) {
    return this.taskForm.controls[controlName].invalid && this.taskForm.controls[controlName].touched;
  }

  saveFile(event: any) {
    this.taskForm.controls['files'].setValue(event.target.files[0]);
  }

  goBack(): void {
    window.history.back();
  }
}
