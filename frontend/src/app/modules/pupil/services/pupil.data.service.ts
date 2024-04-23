import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {EnvironmentService} from "../../../../environments/environment.service";
import { Subject } from 'src/app/models/Subject';
import { Pupil } from 'src/app/models/Pupil';


@Injectable({
  providedIn: 'root'
})
export class PupilDataService {

  private subjects: Subject[] | null = null;
  private pupil: Pupil | null = null;
  private currentSubject: Subject | null = null;

  public getSubjects(): Subject[]|null {
    return this.subjects;
  }

  public setSubjects(subjects: Subject[]|null) {
    this.subjects = subjects;
  }

  public getPupil(): Pupil|null {
    return this.pupil;
  }

  public setPupil(pupil: Pupil|null) {
    this.pupil = pupil;
  }

  public getCurrentSubject(): Subject|null {
    return this.currentSubject;
  }

  public setCurrentSubject(currentSubject: Subject|null) {
    this.currentSubject = currentSubject;
  }

}
