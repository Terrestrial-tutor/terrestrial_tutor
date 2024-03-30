import {Component, OnDestroy, OnInit} from '@angular/core';
import {TutorService} from "./services/tutor.service";
import {Subject} from "rxjs";
import {Router} from "@angular/router";
import {dataService} from "./services/data.service";
import {Homework} from "../../models/Homework";
import {Store} from "@ngrx/store";
import {clearHomeworkState, createHomework} from "./storage/homework.actions";
import * as HomeworkSelectors from "./storage/homework.selectors"
import * as HomeworkActions from "./storage/homework.actions"

@Component({
  selector: 'app-tutor',
  templateUrl: './tutor.component.html',
  styleUrls: ['./tutor.component.css']
})
export class TutorComponent implements OnInit {

  constructor(private tutorService: TutorService,
              private router: Router,
              private dataService: dataService,
              private store: Store) { }

  currentSubjects: any;

  ngOnInit(): void {
    this.tutorService.getTutorSubjects().subscribe(subjects =>
      this.currentSubjects = subjects);
    this.store.select(HomeworkSelectors.getHomework).subscribe(homework => {
      if (homework.state != 'saved') {
        let storageHomeworkId = sessionStorage.getItem('homeworkId');
        if (storageHomeworkId) {
          let id = parseInt(storageHomeworkId);
          this.store.dispatch(HomeworkActions.deleteHomework({id}))
        }
      }
    });
    sessionStorage.removeItem('homeworkId');
    this.store.dispatch(clearHomeworkState());
  }

  addHW(subject: any) {
    this.store.dispatch(createHomework({subject}))
    this.store.select(HomeworkSelectors.getHomework).subscribe(homework => {
      if (homework.state == 'created') {
        sessionStorage.setItem('homeworkId', JSON.stringify(homework.homework?.id));
        this.router.navigate(['/tutor/constructor']);
      }
    });
  }
}
