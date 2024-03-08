import { Component, OnInit } from '@angular/core';
import {TokenStorageService} from "../../security/token-storage.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-support',
  templateUrl: './support.component.html',
  styleUrls: ['./support.component.css']
})
export class SupportComponent implements OnInit {

  constructor(private tokenService: TokenStorageService,
              private router: Router,) {}

  ngOnInit(): void {
  }

  logoutPupil(): void {
    this.tokenService.logOut();
  }

  addTask() {
    this.router.navigate(['support/task/add']);
  }
}
