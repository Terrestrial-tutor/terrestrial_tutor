import { Component, OnInit } from '@angular/core';
import {PupilService} from "../../pupil/services/pupil.service";
import {Pupil} from "../../../models/Pupil";
import {PupilSelect} from "../../../models/PupilSelect";
import {dataService} from "../services/data.service";
import {Homework} from "../../../models/Homework";
import {Router} from "@angular/router";
import {UntypedFormControl} from "@angular/forms";

@Component({
  selector: 'app-pupils-add-homework',
  templateUrl: './pupils-add-homework.component.html',
  styleUrls: ['./pupils-add-homework.component.css']
})
export class PupilsAddHomeworkComponent implements OnInit {

  constructor(private pupilService: PupilService,
              private dataService: dataService,
              private router: Router,) { }

  allPupils: PupilSelect[] = [];
  currentPupils: Pupil[] | null = [];
  homework: Homework | null = null;
  isNewPupilsLoaded = false;
  filteredPupils: PupilSelect[] = [];
  filter = new UntypedFormControl('');

  ngOnInit(): void {
    this.homework = this.dataService.getCurrentHomework();
    this.currentPupils = this.dataService.getCurrentPupils();
    this.pupilService.getAllPupils().subscribe(pupils => {
      for (let i = 0; i < pupils.length; i++) {
        if (this.currentPupils &&
          this.currentPupils[i].id == pupils[i].id &&
          this.homework &&
          pupils[i].subject == this.homework.subject) {
          this.allPupils.push(new PupilSelect(pupils[i], true));
        } else {
          this.allPupils.push(new PupilSelect(pupils[i], false));
        }
        this.filteredPupils = this.allPupils;
      }
      this.isNewPupilsLoaded = true;
    })
  }

  getSelectedPupilsIds() {
    let selectedPupilsIds = [];
    let selectedPupils = [];
    for (let pupil of this.allPupils) {
      if (pupil.isSelected) {
        selectedPupilsIds.push(pupil.pupil.id);
        selectedPupils.push(pupil.pupil);
      }
    }
    this.currentPupils = selectedPupils;
    return selectedPupilsIds;
  }

  search(text: any) {
    text = text.toLowerCase();
    this.filteredPupils = this.allPupils.filter(pupil => {
      return pupil.pupil.username.toLowerCase().includes(text) ||
        pupil.pupil.name.toLowerCase().includes(text) ||
        pupil.pupil.surname.toLowerCase().includes(text) ||
        pupil.pupil.patronymic.toLowerCase().includes(text);
    });
  }

  submit() {
    if (this.homework) {
      this.homework.pupilIds = this.getSelectedPupilsIds();
      this.dataService.setCurrentPupils(this.currentPupils);
      this.router.navigate(['tutor/constructor']);
    }
  }

}
