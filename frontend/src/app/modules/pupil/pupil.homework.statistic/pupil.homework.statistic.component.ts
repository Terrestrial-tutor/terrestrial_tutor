import { Component } from '@angular/core';
import {PupilService} from "../services/pupil.service";
import {Router} from "@angular/router";
import {PupilDataService} from "../services/pupil.data.service";
import {Pupil} from "../../../models/Pupil";
import {HomeworkAnswers} from "../../../models/HomeworkAnswers";
import {JsonPipe, KeyValuePipe, NgForOf} from "@angular/common";

@Component({
  selector: 'app-pupil.homework.statistic',
  standalone: true,
  imports: [
    NgForOf,
    KeyValuePipe,
    JsonPipe
  ],
  templateUrl: './pupil.homework.statistic.component.html',
  styleUrl: './pupil.homework.statistic.component.css'
})
export class PupilHomeworkStatisticComponent {

  currentHomework: number | null = null;
  pupil: Pupil | null = null;
  // @ts-ignore
  homeworkAnswers: HomeworkAnswers;

  constructor(private pupilService: PupilService,
              private router: Router,
              private pupilDataService: PupilDataService,) {}

  ngOnInit(): void {
    let  serviceHomework = this.pupilDataService.getCurrentHomework()?.id;
    if (serviceHomework) {
      this.currentHomework = serviceHomework;
    } else {
      let homeworkId = sessionStorage.getItem('currentHomework');
      if (homeworkId) {
        this.currentHomework = parseInt(homeworkId);
      }
    }
    if (this.pupilDataService.getPupil()) {
      this.pupil = this.pupilDataService.getPupil();
      this.getStatistic();
    } else if (sessionStorage.getItem('currentPupil')) {
      this.pupilService.getPupilById(sessionStorage.getItem('currentPupil'));
      this.getStatistic();
    } else {
      this.pupilService.getCurrentUser().subscribe(pupil => {
        this.pupil = pupil
        this.getStatistic();
      });
    }
  }

  getStatistic() {
    if (this.pupil) {
      let pupilId = this.pupil.id;
      if (this.currentHomework && pupilId) {
        this.pupilService.getHomeworkAnswers(this.currentHomework, pupilId).subscribe(answers =>
          this.homeworkAnswers = answers);
      }
    }
  }

}
