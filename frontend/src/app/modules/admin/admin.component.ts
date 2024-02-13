import {Component, OnInit} from '@angular/core';
import {Check} from "../../models/Check";
import {AdminService} from "./services/admin.service";
import {TokenStorageService} from "../../security/token-storage.service";

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css'],
})
export class AdminComponent implements OnInit {

  checks: Check[] = []

  constructor(private adminService: AdminService,
              private tokenService: TokenStorageService) {
  }

  ngOnInit(): void {
    this.adminService.getAllChecks()
      .subscribe(checks => this.checks = checks);
  }

  deleteCheck(id: number) {
    this.adminService.deleteCheck(id).subscribe(check => this.checks.splice(check));
  }

  logOut(): void {
    this.tokenService.logOut();
  }

}
