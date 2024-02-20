import {Component, OnInit} from '@angular/core';
import {Check} from "../../models/Check";
import {AdminService} from "./services/admin.service";
import {TokenStorageService} from "../../security/token-storage.service";
import {Subject} from "../../models/Subject";
import {SubjectsService} from "../subjects/services/subjects.service";
import {PupilService} from "../pupil/services/pupil.service";
import {TutorList} from "../../models/TutorList";
import {Pupil} from "../../models/Pupil";
import {MatDialog, MatDialogConfig} from "@angular/material/dialog";
import {PupilsDialogComponent} from "./dialogs/pupils-dialog/pupils-dialog.component";
import {HttpClient} from "@angular/common/http";
import {ConstantsComponent} from "../../constants/constants.component";

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css'],
})
export class AdminComponent implements OnInit {

  checks: Check[] = []
  selectedSubject: string | undefined;
  subjects: Subject[] | undefined;
  tutorsBySubjects: TutorList[] = [];
  // @ts-ignore
  selectedTutor: TutorList;
  pupils: Pupil[] = [];
  searchTutor: string = '';
  filteredTutors: TutorList[] = [];
  isChecksPageLoaded: boolean = false;
  isPupilsLoaded: boolean = true;

  constructor(private adminService: AdminService,
              private tokenService: TokenStorageService,
              private subjectsService: SubjectsService,
              private pupilService: PupilService,
              public dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.adminService.getAllChecks()
      .subscribe(checks => {
        this.checks = checks;
        this.isChecksPageLoaded = true;
      });
    this.getAllSubjects();
  }

  deleteCheck(id: number) {
    this.adminService.deleteCheck(id).subscribe(check => {
      this.checks.splice(check);
      window.location.reload();
    });
  }

  logOut(): void {
    this.tokenService.logOut();
  }

  /*addSubjects(){
    this.pupilService.addSubjects([this.selectedSubject], this.pupil?.id).subscribe();
  }*/

  getAllSubjects(): void {
    this.subjectsService.getAllSubjects().subscribe(data => {
      this.subjects = data;
    })
  }

  findTutorsBySubject() {
    this.adminService.findTutorsBySubject(this.selectedSubject).subscribe(tutors => {
      this.tutorsBySubjects = tutors;
      this.filteredTutors = this.tutorsBySubjects;
    })
  }

  getTutorPupilsBySubject() {
    this.isPupilsLoaded = false;
    this.adminService.getTutorPupilsBySubject(this.selectedSubject, this.selectedTutor.id).subscribe(data => {
      this.pupils = data;
      this.isPupilsLoaded = true;
    })
  }

  openDialog() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.width = '50%';
    dialogConfig.height = '500px';
    dialogConfig.data = {subject: this.selectedSubject, tutor: this.selectedTutor};
    const dialogRef = this.dialog.open(PupilsDialogComponent, dialogConfig);
    dialogRef.afterClosed().subscribe(result => {
      let newPupilsIds: number[] = [];
      for (let pupil of result) {
        newPupilsIds.push(pupil.id);
      }
      this.pupilService.addSubjects(this.selectedSubject, newPupilsIds).subscribe();
      this.adminService.addPupilsForTutor(newPupilsIds, this.selectedTutor.id).subscribe(pupils => {
        this.pupils = pupils;
      });
    })
  }

  filterTutors() {
    if (!this.searchTutor) {
      this.filteredTutors = [];
      return;
    }
    this.filteredTutors = this.tutorsBySubjects.filter(item => {
      return item.username.toLowerCase().includes(this.searchTutor.toLowerCase()) ||
        item.name.toLowerCase().includes(this.searchTutor.toLowerCase()) ||
        item.surname.toLowerCase().includes(this.searchTutor.toLowerCase()) ||
        item.patronymic.toLowerCase().includes(this.searchTutor.toLowerCase());
    });
  }
}
