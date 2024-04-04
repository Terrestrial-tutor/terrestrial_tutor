import {Component, OnDestroy, OnInit} from '@angular/core';
import {TutorService} from "./services/tutor.service";
import {Subject} from "rxjs";
import {Router} from "@angular/router";
import {dataService} from "./services/data.service";
import {Homework} from "../../models/Homework";

@Component({
  selector: 'app-tutor',
  templateUrl: './tutor.component.html',
  styleUrls: ['./tutor.component.css']
})
export class TutorComponent implements OnInit {

  constructor(private tutorService: TutorService,
              private router: Router,
              private dataService: dataService,) { }

  currentSubjects: any;

  ngOnInit(): void {
    this.tutorService.getTutorSubjects().subscribe(subjects =>
      this.currentSubjects = subjects);
  }

  addHW(subject: any) {
    if (!this.dataService.getCurrentHomework() ||
      this.dataService.getCurrentHomework() == null ||
      this.dataService.getCurrentHomework()!.tasksCheckingTypes.size == null) {
      let newHomework: Homework = {pupilIds: [], subject: subject.subjectName, tasksCheckingTypes: new Map<number, string>()};
      this.dataService.setCurrentHomework(newHomework);
    }
    this.router.navigate(['/tutor/constructor']);
  }
}
