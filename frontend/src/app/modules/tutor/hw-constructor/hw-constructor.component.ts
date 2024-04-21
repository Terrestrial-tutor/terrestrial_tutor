import {Component, HostListener, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {dataService} from "../services/data.service";
import {Router} from "@angular/router";
import {TutorService} from "../services/tutor.service";
import {Homework} from "../../../models/Homework";
import {UntypedFormBuilder, UntypedFormGroup, Validators} from "@angular/forms";
import {Task} from "../../../models/Task";
import {CodemirrorComponent} from "@ctrl/ngx-codemirror";
import {Subscription} from "rxjs";
import {TutorDataService} from "../storage/tutor.data.service";

@Component({
  selector: 'app-hw-constructor',
  templateUrl: './hw-constructor.component.html',
  styleUrls: ['./hw-constructor.component.css']
})
export class HwConstructorComponent implements OnInit {
  @ViewChild('codemirrorComponent') codemirror: CodemirrorComponent | undefined;

  constructor(private tutorService: TutorService,
              private dataService: dataService,
              private router: Router,
              private fb: UntypedFormBuilder,
              private tutorDataService: TutorDataService,) { }

  homework: Homework | null = null;
  //@ts-ignore
  hwForm: UntypedFormGroup;
  isCollapsed: boolean[] = [];
  update: boolean = false;
  currentTasks: Task[] | null = null;
  pageLoaded: boolean = false;
  subscriptions: Subscription[] = [];

  ngOnInit(): void {
    if (this.tutorDataService.getHomework()) {
      this.homework = this.tutorDataService.getHomework();
      this.initFields();
      this.initForm();
    } else {
      let homework: number = Number(sessionStorage.getItem("homeworkId"));
      this.tutorService.getHomework(homework).subscribe(homework => {
        this.homework = homework;
        this.initFields();
        this.initForm();
      })
    }
  }

  initFields() {
    if (this.homework) {
      this.currentTasks = this.homework.tasks;
      this.isCollapsed = [];
      if (this.homework?.tasksCheckingTypes) {
        for (let task of this.currentTasks) {
          this.isCollapsed.push(true);
        }
      }
    }
  }

  initForm(): void {
    this.hwForm = this.fb.group( {
      name: [this.homework?.name, Validators.compose([Validators.required])],
      deadLine: [this.homework?.deadLine, Validators.compose([Validators.required])],
      targetTime: ['', Validators.compose([Validators.required])],
    });
    if (this.homework != null)
      this.pageLoaded = true;
  }

  addTasks(): void {
    this.saveHomework();
    this.tutorService.saveHomework(this.homework).subscribe(data => {
      this.router.navigate(['/tutor/constructor/hw/add/task'])
    })
  }

  saveHomework() {
    this.homework!.name = this.hwForm.controls['name'].value;
    this.homework!.deadLine = this.hwForm.controls['deadLine'].value;
    this.homework!.targetTime = this.hwForm.controls['targetTime'].value;
    this.tutorDataService.setHomework(this.homework);
  }

  submit() {
    if (this.homework) {
      this.tutorService.saveHomework(this.homework).subscribe(id => {
        this.tutorDataService.setHomework(null);
        sessionStorage.removeItem("homeworkId");
        this.router.navigate(['/tutor']);
      });
    }
  }

  checkImage(file: string): boolean {
    return file.endsWith('.jpg') || file.endsWith('.png') || file.endsWith('.jpeg');
  }

  codemirrorInit() {
    if (this.codemirror != undefined) {
      this.codemirror.codeMirror?.refresh();
    }
  }

  setChecking(index: number, type: string) {
    this.homework!.tasksCheckingTypes[index] = type;
  }

  getChecking(index: number) {
    return this.homework?.tasksCheckingTypes[index];
  }

  addPupils() {
    this.saveHomework();
    this.tutorService.saveHomework(this.homework).subscribe(data => {
      this.router.navigate(['tutor/constructor/add/pup']);
    })
  }
}
