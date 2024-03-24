import { Component, OnInit } from '@angular/core';
import {AbstractControl, FormArray, FormBuilder, FormGroup, Validators} from "@angular/forms";
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
  answerTypes = ['Варианты', 'Текст или значение', 'Код'];
  code = 'function hello() {\n' +
    '  console.log(\'Hello, world!\');\n' +
    '}';
  options = {
    lineNumbers: true,
    theme: 'material',
    mode: 'text/x-c++src',
    matchBrackets: true,
    autoCloseBrackets: true,
    extraKeys: {'Ctrl-Space': 'autocomplete'}
  };

  constructor(private fb: FormBuilder,
              private subjectsService: SubjectsService,
              private supportService: SupportService,
              private router: Router,) {
  }

  ngOnInit(): void {
    this.taskForm = this.createTaskForm();
    this.getAllSubjects();
  }

  private createTaskForm() {
    return this.fb.group({
      taskName: ['', Validators.compose([Validators.required])],
      taskText: ['', Validators.compose([Validators.required])],
      taskAns: this.fb.array([
        this.fb.control('', Validators.compose([Validators.required]))
      ], Validators.compose([Validators.required])),
      selectedSubject: ['Выбирете предмет', Validators.compose([(subject) => {
        if (subject.value == 'Выбирете предмет') {
          return {subjectNotSelected: false};
        } else {
          return null;
        }
      }])],
      answerType: ['Выбирете тип', Validators.compose([(answerType) => {
        if (answerType.value == 'Выбирете тип') {
          return {answerType: false};
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
      answerType: this.taskForm.controls['answerType'].value,
      taskText: this.taskForm.controls['taskText'].value,
      answer: this.taskForm.controls['taskAns'].value,
      checking: 1,
      subject: this.taskForm.controls['selectedSubject'].value,
      level1: this.taskForm.controls['level1'].value,
      level2: this.taskForm.controls['level2'].value
    };
    console.log(this.taskForm.controls['files'].value)
    this.supportService.addTask(task, this.taskForm.controls['files'].value).subscribe(data => {
      this.router.navigate(['/support']);
      console.log('Task Added');
    })
  }

  invalid(controlName: string) {
    return this.taskForm.controls[controlName].invalid && this.taskForm.controls[controlName].touched;
  }

  invalidAnswer(answer: any) {
    return answer.invalid && answer.touched;
  }

  saveFile(event: any) {
    this.taskForm.controls['files'].setValue(event.target.files);
  }

  goBack(): void {
    window.history.back();
  }

  get taskAns() {
    return this.taskForm.get('taskAns') as FormArray;
  }

  addAns() {
    this.taskAns.push(this.fb.control('', Validators.compose([Validators.required])));
  }

  deleteAns(index: number) {
    this.taskAns.removeAt(index);
  }

  typeChange(answerType: any) {
    this.taskForm.controls['taskAns'] = (this.fb.array([
      this.fb.control('', Validators.compose([Validators.required]))
    ], Validators.compose([Validators.required])));
    this.taskForm.controls['answerType'].setValue(answerType);
  }
}
