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

  constructor(private pupilDataService: PupilDataService,
    private pupilService: PupilService,
    private router: Router,
  ) {}

  ngOnInit(): void {
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
        this.homeworks = this.pupil?.homeworks.filter((homework) => {
          if (homework.subject == this.subject) {
            return homework;
          }
          return null;
        });
      }
    } else {
      this.pupilService.getCurrentUser().subscribe(pupil => {
        this.pupil = pupil;
        this.pupilDataService.setPupil(pupil)
        if (this.pupil?.homeworks) {
          this.homeworks = this.pupil?.homeworks;
        }
      })
    }
  }

  submit(homework: Homework) {
    if (homework.id) {
      sessionStorage.setItem('currentHomework', JSON.stringify(homework.id));
    }
    this.pupilDataService.setCurrentHomework(homework);
    this.router.navigate(['pupil/homework']);
  }
}
