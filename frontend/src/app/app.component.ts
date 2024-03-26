import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {Router} from "@angular/router";
import {TokenStorageService} from "./security/token-storage.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class AppComponent implements OnInit{

  constructor(private router: Router,
              private tokenService: TokenStorageService,) {
  }

  ngOnInit(): void {
  }

  logout(): void {
    this.tokenService.logOut();
  }

  checkLogin(): boolean {
    return this.tokenService.getUser();
  }
}
