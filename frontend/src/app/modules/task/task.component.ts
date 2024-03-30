import { Component, OnInit } from '@angular/core';
import {AbstractControl, UntypedFormArray, UntypedFormBuilder, UntypedFormGroup, Validators} from "@angular/forms";
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
  taskForm: UntypedFormGroup;
  subjects: Subject[] | undefined;
  answerTypes = ['Варианты', 'Текст или значение', 'Код'];
  options = {
    lineNumbers: true,
    theme: 'material',
    mode: 'python',
    matchBrackets: true,
    autoCloseBrackets: true,
    extraKeys: {'Ctrl-Space': 'autocomplete'}
  };

  constructor(private fb: UntypedFormBuilder,
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
      tableRows: [''],
      tableCols: [''],
      table: [[
        [this.fb.control(''), this.fb.control(''), this.fb.control('')],
      ]]
    })
  }

  getAllSubjects(): void {
    this.subjectsService.getAllSubjects().subscribe(data => {
      this.subjects = data;
    })
  }

  submit() {
    let task: Task = {
      id: 0,
      name: this.taskForm.controls['taskName'].value,
      answerType: this.taskForm.controls['answerType'].value,
      taskText: this.taskForm.controls['taskText'].value,
      answers: this.taskForm.controls['taskAns'].value,
      checking: 1,
      subject: this.taskForm.controls['selectedSubject'].value,
      level1: this.taskForm.controls['level1'].value,
      level2: this.taskForm.controls['level2'].value,
      table: this.tableToJson(),
      files: this.taskForm.controls['files'].value,
    };

    this.supportService.addTask(task).subscribe(data => {
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
    return this.taskForm.get('taskAns') as UntypedFormArray;
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

  get table() {
    return this.taskForm.get('table') as UntypedFormArray;
  }

  tableToJson() {
    let tableArray: any[][] = [];
    let table = this.taskForm.controls['table'].value;
    for (let i = 0; i < table.length; i++) {
      tableArray.push([])
      for (let col of table[i]) {
        tableArray[i].push(col.value);
      }
    }
    return JSON.stringify(tableArray);
  }

  renderTable() {
    let newTable: any[][] = [];
    let rows = this.taskForm.controls['tableRows'].value;
    let cols = this.taskForm.controls['tableCols'].value;
    for (let i = 0; i < rows; i++) {
      newTable.push([]);
      for (let j = 0; j < cols; j++) {
        newTable[i].push(this.fb.control(['']));
      }
    }
    this.taskForm.controls['table'].setValue(newTable);
  }

  test(content: any) {
    console.log(content);
  }

  protected readonly toString = toString;
}
