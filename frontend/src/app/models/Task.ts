import {Subject} from "./Subject";

export interface Task {
  name: string;
  checking: number;
  answerType: string;
  taskText: string;
  answer: string[];
  subject: string;
  level1: string;
  level2: string;
}
