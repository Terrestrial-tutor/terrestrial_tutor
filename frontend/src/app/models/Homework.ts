export interface Homework {
  name: string;
  targetTime: number;
  pupilIds: number[];
  tasksCheckingTypes: {[key: number]: string};
  deadLine: Date;
  subject: string;
  tasksIds: number[];
}
