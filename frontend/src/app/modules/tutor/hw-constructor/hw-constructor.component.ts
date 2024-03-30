import {Component, HostListener, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {dataService} from "../services/data.service";
import {Router} from "@angular/router";
import {TutorService} from "../services/tutor.service";
import {Homework} from "../../../models/Homework";
import {UntypedFormBuilder, UntypedFormControl, UntypedFormGroup, Validators} from "@angular/forms";
import {Task} from "../../../models/Task";
import {CodemirrorComponent} from "@ctrl/ngx-codemirror";
import {select, Store} from "@ngrx/store";
import * as HomeworkSelectors from "../storage/homework.selectors"
import * as HomeworkActions from "../storage/homework.actions"

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
              private store: Store,) { }

  homework: Homework | null = null;
  //@ts-ignore
  hwForm: UntypedFormGroup;
  isCollapsed: boolean[] = [];
  update: boolean = false;
  currentTasks: Task[] | null = null;

  ngOnInit(): void {
    this.store.select(HomeworkSelectors.getHomework).subscribe(homework => {
      if (homework.state == 'init' && sessionStorage.getItem('homeworkId')) {
        let storageHomeworkId = sessionStorage.getItem('homeworkId');
        if (storageHomeworkId) {
          let id = parseInt(storageHomeworkId);
          this.store.dispatch(HomeworkActions.getHomeworkFromApi({id}))
        }
      }
      this.store.select(HomeworkSelectors.getHomework).subscribe(
        homework => this.initFields(homework.homework)
      )
    });
  }

  initFields(homework: Homework | null) {
    console.log(homework);
    this.homework = homework;
    if (this.homework) {
      this.currentTasks = this.homework.tasks;
      this.isCollapsed = [];
      if (this.homework?.tasksCheckingTypes) {
        for (let task of this.currentTasks) {
          this.isCollapsed.push(true);
        }
      }
      this.initForm();
    }
  }

  initForm(): void {
    this.hwForm = this.fb.group( {
      name: new UntypedFormControl(this.homework?.name, [Validators.required]),
      deadLine: new UntypedFormControl(this.homework?.deadLine, [Validators.required]),
      targetTime: new UntypedFormControl('', [Validators.required]),
    });
  }

  addTasks(): void {
    this.router.navigate(['/tutor/constructor/hw/add/task']);
  }

  submit() {
    if (this.homework) {
      this.tutorService.addHomework(this.homework).subscribe(id => {
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

  @HostListener('window:beforeunload', ['$event'])
  unloadHandler(event: Event) {
    this.update = true;
  }

  protected readonly indexedDB = indexedDB;

  setChecking(index: number, type: string) {
    // if (typeof this.homework?.tasksCheckingTypes)
    // this.homework?.tasksCheckingTypes.set(index, type);
  }

  getChecking(index: number) {
    return 1;
    // return this.homework?.tasksCheckingTypes.get(index);
  }

  addPupils() {
    this.router.navigate(['tutor/constructor/add/pup']);
  }
}
