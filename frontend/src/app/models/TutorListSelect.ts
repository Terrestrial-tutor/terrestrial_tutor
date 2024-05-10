import { TutorList } from "./TutorList";

export class TutorListSelect {
  isSelected: boolean;
  tutor: TutorList;

  constructor(tutor: TutorList, isSelected:boolean){
    this.isSelected = isSelected;
    this.tutor = tutor;
  }
}
