import { Component, OnInit } from '@angular/core';
import {PupilSelect} from "../../models/PupilSelect";
import {Pupil} from "../../models/Pupil";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {Router} from "@angular/router";

@Component({
  selector: 'app-browse-notifications',
  templateUrl: './browse-notifications.component.html',
  styleUrls: ['./browse-notifications.component.css']
})
export class BrowseNotificationsComponent implements OnInit {

  constructor(private modalService: NgbModal,
              private router: Router) { }

  ngOnInit(): void {
  }

  open() {
    this.modalService.open('',
      {
        ariaLabelledBy: 'modal-basic-title', size: 'lg'
      }).
    result.then().
    finally(() => {});
  }

  close(modal: any) {
    modal.close(this.router.navigate(['tutor/']));
  }

}
