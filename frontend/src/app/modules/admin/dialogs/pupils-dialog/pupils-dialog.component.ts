import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {AdminService} from "../../services/admin.service";
import {Pupil} from "../../../../models/Pupil";
import {SelectionModel} from "@angular/cdk/collections";
import {MatTableDataSource} from "@angular/material/table";
import {MatCheckboxChange} from "@angular/material/checkbox";

@Component({
  selector: 'app-pupils-dilog',
  templateUrl: './pupils-dialog.component.html',
  styleUrls: ['./pupils-dialog.component.css']
})
export class PupilsDialogComponent implements OnInit {
  pupils  = new MatTableDataSource<Pupil>([]);
  displayedColumns: string[] = ['select', 'surname', 'name', 'patronymic', 'username'];
  selection = new SelectionModel<Pupil>(true, []);
  searchPupil: string = '';
  isPageLoaded: boolean = false;

  constructor(@Inject(MAT_DIALOG_DATA) public data: {
    subject: string;
    tutor: number;
  },
              public dialogRef: MatDialogRef<PupilsDialogComponent>,
              private adminService: AdminService) {
    this.pupils.data = [];
  }

  closeDialog(): void {
    this.dialogRef.close();
  }

  ngOnInit(): void {
    this.adminService.findPupilsWithoutSubject(this.data.subject).subscribe(pupils => {
      this.isPageLoaded = true;
      this.pupils.data = pupils;
    })
  }

  applyFilter() {
    this.pupils.filter = this.searchPupil.trim().toLowerCase();
  }

  onSave() {
    this.dialogRef.close(this.selection.selected);
  }
}
