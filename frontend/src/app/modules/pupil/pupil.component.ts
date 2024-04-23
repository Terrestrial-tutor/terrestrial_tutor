import { Component } from '@angular/core';
import {PupilService} from "./services/pupil.service";
import {Pupil} from "../../models/Pupil";
import {TokenStorageService} from "../../security/token-storage.service";
import {SubjectsService} from "../subjects/services/subjects.service";
import {Subject} from "../../models/Subject";
import { Router } from '@angular/router';
import { PupilDataService } from './services/pupil.data.service';

@Component({
  selector: 'app-pupil',
  templateUrl: './pupil.component.html',
  styleUrls: ['./pupil.component.css']
})
export class PupilComponent {

  pupil: Pupil | null = null;
  currentSubjects: Subject[] | null = null;

  constructor(private router: Router,
    private pupilDataService: PupilDataService,
    private pupilService: PupilService,) { }

  ngOnInit(): void {
    this.pupilService.getCurrentUser().subscribe(pupil => {
      this.pupil = pupil;
      this.currentSubjects = pupil.subjects;
      this.pupilDataService.setSubjects(this.currentSubjects);
    })
  }

}
