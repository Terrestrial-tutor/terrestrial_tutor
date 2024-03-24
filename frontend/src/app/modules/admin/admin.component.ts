import {Component, OnInit, PipeTransform} from '@angular/core';
import {Check} from "../../models/Check";
import {AdminService} from "./services/admin.service";
import {TokenStorageService} from "../../security/token-storage.service";
import {Subject} from "../../models/Subject";
import {SubjectsService} from "../subjects/services/subjects.service";
import {PupilService} from "../pupil/services/pupil.service";
import {TutorList} from "../../models/TutorList";
import {Pupil} from "../../models/Pupil";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {PupilSelect} from "../../models/PupilSelect";
import {FormControl} from "@angular/forms";

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
  filteredTutors: TutorList[] = [];
  isChecksPageLoaded: boolean = false;
  isPupilsLoaded: boolean = true;
  isNewPupilsLoaded: boolean = true;
  newPupils: PupilSelect[] = [];
  active: any;
  filter = new FormControl('');
  filteredPupils: PupilSelect[] = [];

  constructor(private adminService: AdminService,
              private tokenService: TokenStorageService,
              private subjectsService: SubjectsService,
              private pupilService: PupilService,
              private modalService: NgbModal) {
  }

  ngOnInit(): void {
    this.selectedSubject = "Выбирете предмет";
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

  search(text: any) {
    this.filteredPupils = this.newPupils.filter(pupil => {
      return pupil.pupil.username.toLowerCase().includes(text) ||
        pupil.pupil.name.toLowerCase().includes(text) ||
        pupil.pupil.surname.toLowerCase().includes(text) ||
        pupil.pupil.patronymic.toLowerCase().includes(text);
    });
  }

  open(content: any) {
    this.newPupils = [];
    this.adminService.findPupilsWithoutSubject(this.selectedSubject).subscribe(pupils => {
      this.newPupils = pupils.map((pupil: any) => {
        return new PupilSelect(pupil, false);
      });
      this.filteredPupils = this.newPupils;
      this.isPupilsLoaded = true;
    });
    this.filter.valueChanges.subscribe((text) => {
      this.search(text);
    });
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then(selectedPupils => {
      let newPupilsIds = selectedPupils.map((pupil: Pupil) => {
        return pupil.id;
      })
      this.pupilService.addSubjects(this.selectedSubject, newPupilsIds).subscribe();
      this.adminService.addPupilsForTutor(newPupilsIds, this.selectedTutor.id).subscribe(pupils => {
        this.pupils = pupils;
      });
    }).finally(() => {});
    this.isPupilsLoaded = false;
  }

  close(modal: any) {
    modal.close(this.newPupils.filter(pupil => pupil.isSelected).map(pupil => pupil.pupil));
  }

}
