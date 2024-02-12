import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../../../environments/environment";
import {ConstantsComponent} from "../../../constants/constants.component";

const CHECK_API = ConstantsComponent.MAIN_API_PATH + 'check/';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient) { }

  getAllChecks(): Observable<any>{
    return this.http.get(CHECK_API + 'all');
  }

  deleteCheck(id: number): Observable<any>{
    return this.http.delete(CHECK_API + `close/${id}`);
  }

}
