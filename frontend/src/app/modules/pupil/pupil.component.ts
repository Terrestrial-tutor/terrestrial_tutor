import { Component } from '@angular/core';
import {PupilService} from "./services/pupil.service";
import {Pupil} from "../../models/Pupil";
import {MatTab, MatTabGroup} from "@angular/material/tabs";
import {TokenStorageService} from "../../security/token-storage.service";

@Component({
  selector: 'app-pupil',
  templateUrl: './pupil.component.html',
  styleUrls: ['./pupil.component.css']
})
export class PupilComponent {

  pupil: Pupil | undefined;

  constructor(private pupilService: PupilService,
              private tokenService: TokenStorageService) {}

  ngOnInit(): void {
    // @ts-ignore
    this.pupilService.getCurrentUser().subscribe(data => {
      this.pupil = data;
    });
  }

  logoutPupil(): void {
    this.tokenService.logOut();
  }

  addSubjects(){
    this.pupilService.addSubjects(['информатика']).subscribe();
  }

}
