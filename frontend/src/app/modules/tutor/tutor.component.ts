import { Component, OnInit } from '@angular/core';
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
  }

  addHW(subject: any) {
    if (subject.subjectName != this.transferService.getSubjectName()) {
      this.transferService.deleteSubjectName();
      this.transferService.deleteHwTasks();
      this.transferService.setSubjectName(subject.subjectName);
    }

    this.router.navigate(['/tutor/constructor']);
  }

}
