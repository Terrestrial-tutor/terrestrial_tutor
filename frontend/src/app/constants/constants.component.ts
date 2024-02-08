import { Component } from '@angular/core';

@Component({
  selector: 'app-constants',
  standalone: true,
  imports: [],
  templateUrl: './constants.component.html',
  styleUrl: './constants.component.css'
})
export class ConstantsComponent {
  static MAIN_API_PATH: string = 'http://tertut.local/api/';
}
