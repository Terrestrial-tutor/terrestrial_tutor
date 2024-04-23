import {Component, OnDestroy, OnInit} from '@angular/core';
import {TutorService} from "./services/tutor.service";
import {map, Subject, Subscription} from "rxjs";
import {Router} from "@angular/router";
import {dataService} from "./services/data.service";
import {Homework} from "../../models/Homework";
import {Store} from "@ngrx/store";
import {clearHomeworkState, saveHomework} from "./storage/homework.actions";
import * as HomeworkSelectors from "./storage/homework.selectors"
import * as HomeworkActions from "./storage/homework.actions"
import {TutorDataService} from "./storage/tutor.data.service";

@Component({
  selector: 'app-tutor',
  templateUrl: './tutor.component.html',
  styleUrls: ['./tutor.component.css']
})
export class TutorComponent implements OnInit {

  constructor(private tutorService: TutorService,
              private router: Router,
              private dataService: dataService,
              private tutorDataService: TutorDataService,) { }

  currentSubjects: any;
  subscriptions: Subscription[] = [];
  pageLoaded: boolean =  true;

  ngOnInit(): void {
    let homeworkId = Number(sessionStorage.getItem("homeworkId"));
    if (homeworkId) {
      this.tutorService.deleteHomeworkById(homeworkId).subscribe(() => {
        sessionStorage.removeItem("homeworkId");
        this.tutorService.getTutorSubjects().subscribe(subjects =>
          this.currentSubjects = subjects);
      });
    } else {
      this.tutorService.getTutorSubjects().subscribe(subjects =>
        this.currentSubjects = subjects);
    }
  }

  addHW(subject: any) {
    this.pageLoaded = false;
    this.tutorService.createHomework(subject).subscribe(homework => {
      if (homework && homework.id) {
        this.pageLoaded = true;
        this.tutorDataService.setHomework(homework);
        sessionStorage.setItem('homeworkId', JSON.stringify(homework.id));
        this.router.navigate(['/tutor/constructor']);
      }
    });
  }
}
