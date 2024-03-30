import {Component, OnDestroy, OnInit} from '@angular/core';
import {TutorService} from "./services/tutor.service";
import {Subject} from "rxjs";
import {Router} from "@angular/router";
import {TransferService} from "./services/transfer.service";

@Component({
  selector: 'app-tutor',
  templateUrl: './tutor.component.html',
  styleUrls: ['./tutor.component.css']
})
export class TutorComponent implements OnInit {

  constructor(private tutorService: TutorService,
              private router: Router,
              private transferService: TransferService,) { }

  currentSubjects: any;

  ngOnInit(): void {
    this.tutorService.getTutorSubjects().subscribe(subjects =>
      this.currentSubjects = subjects);
    if (this.transferService.getHWId()) {
      let id= localStorage.getItem('HWId');
      this.tutorService.isHomeworkEmpty(id).subscribe(isHomeworkEmpty => {
        if (isHomeworkEmpty) {
          this.tutorService.deleteHomeworkById(id).subscribe();
          this.transferService.deleteHWId()
        }
      })
    }
  }

  addHW(subject: any) {
    this.tutorService.addEmptyHomework(subject.subjectName).subscribe(HWid => {
      this.transferService.setHWId(HWid);
      this.router.navigate(['/tutor/constructor']);
    });
  }

}
