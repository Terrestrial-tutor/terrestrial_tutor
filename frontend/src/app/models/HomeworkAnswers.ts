export interface HomeworkAnswers {
  checkingAnswers: {[key: string]: DetailsAnswer};
}

export class DetailsAnswer {
  check: boolean = false;
  rightAnswer: string = "";
}
