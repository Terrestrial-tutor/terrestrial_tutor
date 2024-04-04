import {Component, HostListener, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {dataService} from "../services/data.service";
import {Router} from "@angular/router";
import {TutorService} from "../services/tutor.service";
import {Homework} from "../../../models/Homework";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {Task} from "../../../models/Task";
import {CodemirrorComponent} from "@ctrl/ngx-codemirror";

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
              private fb: FormBuilder,) { }

  homework: Homework | null = null;
  //@ts-ignore
  hwForm: FormGroup;
  currentSubject: string = "";
  isCollapsed: boolean[] = [];
  update: boolean = false;
  currentTasks: Task[] | null = null;

  ngOnInit(): void {
    this.homework = this.dataService.getCurrentHomework();
    this.currentTasks = this.dataService.getCurrentTasks();
    this.isCollapsed = [];
    if (this.homework?.tasksCheckingTypes) {
      for (let task of this.homework?.tasksCheckingTypes) {
        this.isCollapsed.push(true);
      }
    }
    if (this.homework) {
      this.currentSubject = this.homework?.subject;
    }
    this.initForm();

    // window.addEventListener("beforeunload", (event) => {
    //   event.preventDefault();
    //   event.returnValue = "";
    //   this.ngbToast.show();
    // });
  }

  initForm(): void {
    this.hwForm = this.fb.group( {
      name: new FormControl(this.homework?.name, [Validators.required]),
      deadLine: new FormControl(this.homework?.deadLine, [Validators.required]),
      targetTime: new FormControl('', [Validators.required]),
    });
  }

  addTasks(): void {
    this.saveHomework();
    this.router.navigate(['/tutor/constructor/hw/add/task']);
  }

  saveHomework(): void {
    if (this.homework) {
      this.homework.deadLine = this.hwForm.controls['deadLine'].value;
      this.homework.name = this.hwForm.controls['name'].value;
      this.homework.targetTime = this.hwForm.controls['targetTime'].value;
      this.dataService.setCurrentHomework(this.homework);
    } else {
      this.dataService.setCurrentHomework(this.homework);
    }
  }

  submit() {
    if (this.homework) {
      this.saveHomework()
      this.tutorService.addHomework(this.homework).subscribe(id => {
        this.dataService.setCurrentHomework(null);
        this.dataService.setCurrentTasks(null);
        this.router.navigate(['/tutor']);
      });
    }
  }

  clear() {
    if (this.homework) {
      // @ts-ignore
      this.homework = {subject: this.currentSubject, deadLine: ''};
      this.initForm();
      this.saveHomework();
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

  ngOnDestroy(): void {
    this.saveHomework();
  }

  @HostListener('window:beforeunload', ['$event'])
  unloadHandler(event: Event) {
    this.update = true;
  }

  protected readonly indexedDB = indexedDB;

  setChecking(index: number, type: string) {
    if (typeof this.homework?.tasksCheckingTypes)
    this.homework?.tasksCheckingTypes.set(index, type);
  }

  getChecking(index: number) {
    return this.homework?.tasksCheckingTypes.get(index);
  }

  addPupils() {
    this.saveHomework();
    this.router.navigate(['tutor/constructor/add/pup']);
  }
}
