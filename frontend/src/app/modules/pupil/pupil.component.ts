import { Component } from '@angular/core';
import {PupilService} from "./services/pupil.service";
import {Pupil} from "../../models/Pupil";
import {TokenStorageService} from "../../security/token-storage.service";
import {SubjectsService} from "../subjects/services/subjects.service";
import {Subject} from "../../models/Subject";

@Component({
  selector: 'app-pupil',
  templateUrl: './pupil.component.html',
  styleUrls: ['./pupil.component.css']
})
export class PupilComponent {

  pupil: Pupil | undefined;

  constructor(private pupilService: PupilService,
              private tokenService: TokenStorageService,
              private subjectsService: SubjectsService) {}

  ngOnInit(): void {
    // @ts-ignore
    this.pupilService.getCurrentUser().subscribe(data => {
      this.pupil = data;
    });
  }

}
