import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {EnvironmentService} from "../../../../environments/environment.service";
import { Subject } from 'src/app/models/Subject';


@Injectable({
  providedIn: 'root'
})
export class PupilDataService {

  private subjects: Subject[] | null = null;

  public getSubjects(): Subject[]|null {
    return this.subjects;
  }

  public setSubjects(subjects: Subject[]|null) {
    this.subjects = subjects;
  }

}
