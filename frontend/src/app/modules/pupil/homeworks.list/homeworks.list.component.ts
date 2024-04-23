import { Component } from '@angular/core';
import { PupilDataService } from '../services/pupil.data.service';
import { Pupil } from 'src/app/models/Pupil';
import { PupilService } from '../services/pupil.service';
import { Homework } from 'src/app/models/Homework';
import { Router } from '@angular/router';
import { Subject } from 'src/app/models/Subject';

@Component({
  selector: 'app-homeworks.list',
  templateUrl: './homeworks.list.component.html',
  styleUrls: ['./homeworks.list.component.css']
})
export class HomeworksListComponent {

  pupil: Pupil|null = null;
  homeworks: Homework[]|null = null;
  subject: Subject|null = null;

  constructor(private pupilDataService: PupilDataService,
    private pupilService: PupilService,
    private router: Router,
  ) {}

  ngOnInit(): void {
    if (!this.pupilDataService.getCurrentSubject()) {
      this.router.navigate(['/pupil']);
    } else {
      this.subject = this.pupilDataService.getCurrentSubject();
      if (this.pupilDataService.getPupil()) {
        this.pupil = this.pupilDataService.getPupil();
        if (this.pupil?.homeworks) {
          this.homeworks = this.pupil?.homeworks.filter((homework) => {
            if (homework.subject == this.subject?.subjectName) {
              return null;
            }
            return homework;
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
    
  }
}
