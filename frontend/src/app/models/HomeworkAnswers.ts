import {Homework} from "./Homework";

export interface HomeworkAnswers {
  checkingAnswers: {[key: string]: DetailsAnswer};
  attemptCount: number;
}

export class DetailsAnswer {
  pupilAnswer: string | null = null;
  rightAnswer: string = "";
}
