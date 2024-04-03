import {Task} from "./Task";

export class Homework {
  id?: number
  name?: string;
  targetTime?: number;
  pupilIds?: number[];
  deadLine?: Date;
  subject: string = '';
  tasksCheckingTypes: Map<number, string> = new Map<number, string>();
}
