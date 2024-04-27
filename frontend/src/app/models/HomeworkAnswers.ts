export interface HomeworkAnswers {
  checkingAnswers: {[key: string]: DetailsAnswer};
}

export class DetailsAnswer {
  pupilAnswer: string | null = null;
  rightAnswer: string = "";
}
