import { Component } from '@angular/core';
import {Check} from "../../models/Check";
import {AdminService} from "./services/admin.service";
import {DatePipe, NgForOf} from "@angular/common";

@Component({
  selector: 'app-admin',
  standalone: true,
  imports: [
    NgForOf,
    DatePipe
  ],
  templateUrl: './admin.component.html',
  styleUrl: './admin.component.css'
})
export class AdminComponent {

  checks: Check[] = []

  constructor(private adminService: AdminService) {
  }

  ngOnInit(): void {
    this.adminService.getAllChecks()
      .subscribe(checks => this.checks = checks);
  }

  deleteCheck(id: number) {
    this.adminService.deleteCheck(id).subscribe(check => this.checks.splice(check));
  }

}
