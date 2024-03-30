import {Component, Input, OnInit} from '@angular/core';
import {TransferService} from "../services/transfer.service";
import {Router} from "@angular/router";
import {TutorService} from "../services/tutor.service";
import {Homework} from "../../../models/Homework";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-hw-constructor',
  templateUrl: './hw-constructor.component.html',
  styleUrls: ['./hw-constructor.component.css']
})
export class HwConstructorComponent implements OnInit {

  constructor(private tutorService: TutorService,
              private transferService: TransferService,
              private router: Router,
              private fb: FormBuilder,) { }

  homework: Homework | undefined;
  //@ts-ignore
  hwForm: FormGroup;

  ngOnInit(): void {
    if (this.transferService.getHWId()) {
      this.tutorService.getHomework(this.transferService.getHWId()).subscribe(homeworks => {
        this.homework = homeworks;
      });
    }

    this.hwForm = new FormGroup({
      name: new FormControl(this.homework?.name, [Validators.required]),
      deadLine: new FormControl(this.homework?.deadLine, [Validators.required]),
      targetTime: new FormControl('', [Validators.required]),
    })
  }

  addTasks(): void {
    this.router.navigate(['/tutor/constructor/hw/add/task']);
  }

  test() {
    console.log(this.hwForm);
  }

}
