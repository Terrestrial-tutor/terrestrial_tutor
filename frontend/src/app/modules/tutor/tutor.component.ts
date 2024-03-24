import { Component, OnInit } from '@angular/core';
import {TutorService} from "./services/tutor.service";
import {Subject} from "rxjs";

@Component({
  selector: 'app-tutor',
  templateUrl: './tutor.component.html',
  styleUrls: ['./tutor.component.css']
})
export class TutorComponent implements OnInit {

  constructor(private tutorService: TutorService,) { }

  currentSubjects: any;

  ngOnInit(): void {
    this.tutorService.getTutorSubjects().subscribe(subjects =>
      this.currentSubjects = subjects);
  }

}
