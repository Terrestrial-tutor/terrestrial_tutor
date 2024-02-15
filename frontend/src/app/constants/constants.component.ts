import { Component } from '@angular/core';

@Component({
  selector: 'app-constants',
  templateUrl: './constants.component.html',
  styleUrls: ['./constants.component.css']
})
export class ConstantsComponent {
  //static MAIN_API_PATH: string = 'http://87.249.49.62/api/';
  static MAIN_API_PATH: string = 'http://localhost:8181/api/';
}
