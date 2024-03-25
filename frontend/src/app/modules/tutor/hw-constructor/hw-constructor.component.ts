import {Component, Input, OnInit} from '@angular/core';
import {TransferService} from "../services/transfer.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-hw-constructor',
  templateUrl: './hw-constructor.component.html',
  styleUrls: ['./hw-constructor.component.css']
})
export class HwConstructorComponent implements OnInit {

  constructor(private transferService: TransferService,
              private router: Router) { }

  subject: any;
  // hwTasks: any[];

  ngOnInit(): void {
    this.subject = this.transferService.getSubjectName();
  }

  addTask(): void {
    this.router.navigate(['/tutor/constructor/hw/add/task']);
  }

}
