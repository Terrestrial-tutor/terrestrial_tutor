import {Pupil} from "./Pupil";

export class PupilSelect{
  isSelected:boolean;
  pupil:Pupil;

  constructor(pupil:Pupil, isSelected:boolean){
    this.isSelected = isSelected;
    this.pupil = pupil;
  }
}
