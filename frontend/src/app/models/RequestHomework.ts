import {Task} from "./Task";
import {Homework} from "./Homework";

export class RequestHomework {
  name?: string;
  targetTime?: number;
  pupilIds: number[] | undefined = [];
  tasksCheckingTypes: { [key: number]: string };
  deadLine?: Date;
  subject: string = '';

  constructor(homework: Homework){
    this.name = homework.name;
    this.targetTime = homework.targetTime;
    this.pupilIds = homework.pupilIds;
    this.deadLine = homework.deadLine;
    this.subject = homework.subject;
    let json: { [key: number]: string } = {};
    homework.tasksCheckingTypes.forEach((value, key) => {
      json[key] = value;
    });
    this.tasksCheckingTypes = json;
  }
}
