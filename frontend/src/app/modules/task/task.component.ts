import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Subject} from "../../models/Subject";
import {SubjectsService} from "../subjects/services/subjects.service";

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit {

  // @ts-ignore
  taskForm: FormGroup;
  subjects: Subject[] | undefined;

  constructor(private fb: FormBuilder,
              private subjectsService: SubjectsService,) { }

  ngOnInit(): void {
    this.taskForm = this.createTaskForm();
    this.getAllSubjects();
  }

  private createTaskForm() {
    return this.fb.group({
      taskName: ['', Validators.compose([Validators.required])],
      taskText: ['', Validators.compose([Validators.required])],
      taskAns: ['', Validators.compose([Validators.required])],
      selectedSubject: ['Выбирете предмет', Validators.compose([Validators.required])],

    })
  }

  getAllSubjects(): void {
    this.subjectsService.getAllSubjects().subscribe(data => {
      this.subjects = data;
    })
  }

  check() {
    console.log(this.taskForm.value);
  }
}
