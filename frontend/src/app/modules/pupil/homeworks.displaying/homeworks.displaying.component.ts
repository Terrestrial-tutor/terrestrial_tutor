import { CommonModule } from '@angular/common';
import { ChangeDetectionStrategy, Component, type OnInit } from '@angular/core';
import { PupilDataService } from '../services/pupil.data.service';
import { Router } from '@angular/router';
import { PupilService } from '../services/pupil.service';
import { Pupil } from 'src/app/models/Pupil';
import { Homework } from 'src/app/models/Homework';
import { TutorService } from '../../tutor/services/tutor.service';

@Component({
    selector: 'app-homeworks.displaying',
    templateUrl: './homeworks.displaying.component.html',
    styleUrls: ['./homeworks.displaying.component.css']
})
export class HomeworksDisplayingComponent {

  pupil: Pupil | null = null;
  homework: Homework | null = null;

  constructor(private pupilDataService: PupilDataService,
      private pupilService: PupilService,
      private homeworkService: TutorService,
      private router: Router,
    ) {}

  ngOnInit(): void {
    this.pupil = this.pupilDataService.getPupil();
    if (this.pupil) {
      this.homework = this.pupilDataService.getCurrentHomework();
    } else {
      this.pupilService.getCurrentUser().subscribe(pupil => {
        this.pupil = pupil;
        this.pupilDataService.setPupil(pupil);
        let homework = this.pupil?.homeworks.find(homework => {
          return homework.id == Number(sessionStorage.getItem('currentHomework'));
        });
        
        if (homework) {
          this.pupilDataService.setCurrentHomework(homework);
          this.homework = homework;
        }
      });
    }
  }

}
