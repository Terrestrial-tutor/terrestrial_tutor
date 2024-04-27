import { Component } from '@angular/core';
import { PupilDataService } from '../services/pupil.data.service';
import { Pupil } from 'src/app/models/Pupil';
import { PupilService } from '../services/pupil.service';
import { Homework } from 'src/app/models/Homework';
import { Router } from '@angular/router';

@Component({
  selector: 'app-homeworks.list',
  templateUrl: './homeworks.list.component.html',
  styleUrls: ['./homeworks.list.component.css']
})
export class HomeworksListComponent {

  pupil: Pupil|null = null;
  homeworks: Homework[]|null = null;
  subject: string|null = null;
  collapseHomeworks: boolean = true;
  collapseHomeworksStatistic: boolean = true;
  completedHomeworks: {} = {};

  constructor(private pupilDataService: PupilDataService,
    private pupilService: PupilService,
    private router: Router,
  ) {}

  ngOnInit(): void {
    sessionStorage.removeItem('tryNumber');
    sessionStorage.removeItem('currentHomework');
    this.pupilDataService.setCurrentHomework(null);
    if (!this.pupilDataService.getCurrentSubject()) {
      let subject = sessionStorage.getItem('currentSubject');
      if (subject) {
        this.pupilDataService.setCurrentSubject(JSON.parse(subject));
      }
    }
    this.subject = this.pupilDataService.getCurrentSubject();
    if (this.pupilDataService.getPupil()) {
      this.pupil = this.pupilDataService.getPupil();
      if (this.pupil?.homeworks) {
        this.homeworks = this.pupil?.homeworks.filter((homework: Homework | null) => {
          if (homework && homework.subject == this.subject) {
            return homework;
          }
          return null;
        });
        this.getCompletedHomeworks();
      }
    } else {
      this.pupilService.getCurrentUser().subscribe(pupil => {
        this.pupil = pupil;
        this.pupilDataService.setPupil(pupil);
        this.getCompletedHomeworks();
        if (this.pupil?.homeworks) {
          this.homeworks = this.pupil?.homeworks;
        }
      })
    }
  }

  getCompletedHomeworks() {
    let pupilId = this.pupil?.id;
    if (pupilId) {
      this.pupilService.getCompletedHomeworks(pupilId).subscribe(homeworks => {
        this.completedHomeworks = homeworks;
      });
    }
  }

  getHomeworkById(id: string) {
    if (this.homeworks) {
      return this.homeworks.filter(homework => {
        if (homework.id == parseInt(id)) {
          return homework;
        }
        return null;
      })[0].name;
    }
    return null;
  }

  submit(homework: Homework) {
    if (homework.id) {
      sessionStorage.setItem('currentHomework', JSON.stringify(homework.id));
    }
    this.pupilDataService.setCurrentHomework(homework);
    this.router.navigate(['pupil/homework']);
  }

  submitCompletedHomeworks(tryNumber: number) {
    sessionStorage.setItem('tryNumber', JSON.stringify(tryNumber));
    this.router.navigate(['pupil/homework/statistic']);
  }
}
