import {Component, HostListener, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {dataService} from "../services/data.service";
import {Router} from "@angular/router";
import {TutorService} from "../services/tutor.service";
import {Homework} from "../../../models/Homework";
import {FormControl, UntypedFormBuilder, UntypedFormControl, UntypedFormGroup, Validators} from "@angular/forms";
import {Task} from "../../../models/Task";
import {CodemirrorComponent} from "@ctrl/ngx-codemirror";
import {select, Store} from "@ngrx/store";
import * as HomeworkSelectors from "../storage/homework.selectors"
import * as HomeworkActions from "../storage/homework.actions"
import {map, Observable, Subject, Subscription, takeUntil} from "rxjs";
import {saveHomework} from "../storage/homework.actions";

@Component({
  selector: 'app-hw-constructor',
  templateUrl: './hw-constructor.component.html',
  styleUrls: ['./hw-constructor.component.css']
})
export class HwConstructorComponent implements OnInit, OnDestroy {
  @ViewChild('codemirrorComponent') codemirror: CodemirrorComponent | undefined;

  constructor(private tutorService: TutorService,
              private dataService: dataService,
              private router: Router,
              private fb: UntypedFormBuilder,
              private store: Store,) { }

  homework: Homework | null = null;
  //@ts-ignore
  hwForm: UntypedFormGroup;
  isCollapsed: boolean[] = [];
  update: boolean = false;
  currentTasks: Task[] | null = null;
  pageLoaded: boolean = false;
  subscriptions: Subscription[] = [];

  ngOnInit(): void {
    this.subscriptions.push(this.store.select(HomeworkSelectors.getHomework).subscribe(homework1 => {
      if (homework1.state == 'init' && sessionStorage.getItem('homeworkId')) {
        let storageHomeworkId = sessionStorage.getItem('homeworkId');
        if (storageHomeworkId) {
          let id = parseInt(storageHomeworkId);
          this.store.dispatch(HomeworkActions.getHomeworkFromApi({id}))
        }
      }
      this.subscriptions.push(this.store.select(HomeworkSelectors.getHomework).subscribe(
        homework => {
          console.log(homework);
          this.initFields(homework.homework);
          this.initForm();
        }
      ));
    }));
  }

  initFields(homework: Homework | null) {
    this.homework = structuredClone(homework);
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
    // this.saveHomework();
    this.subscriptions.push(this.tutorService.saveHomework(this.homework).subscribe(homework => {
      this.router.navigate(['/tutor/constructor/hw/add/task'])
    }));
  }

  saveHomework() {
    // this.homework!.name = this.hwForm.controls['name'].value;
    this.homework!.deadLine = this.hwForm.controls['deadLine'].value;
    // this.homework!.targetTime = this.hwForm.controls['targetTime'].value;
  }

  submit() {
    if (this.homework) {
      this.tutorService.saveHomework(this.homework).subscribe(id => {
        this.dataService.setCurrentHomework(null);
        this.dataService.setCurrentTasks(null);
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

  // @HostListener('window:beforeunload', ['$event'])
  // unloadHandler(event: Event) {
  //   console.log('test');
  // }



  setChecking(index: number, type: string) {
    this.homework!.tasksCheckingTypes[index] = type;
  }

  getChecking(index: number) {
    return this.homework?.tasksCheckingTypes[index];
  }

  addPupils() {
    this.router.navigate(['tutor/constructor/add/pup']);
  }

  ngOnDestroy() {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }
}
