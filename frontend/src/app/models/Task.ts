import {Subject} from "./Subject";

export interface Task {
  name: string;
  checking: number;
  answerType: string;
  taskText: string;
  answers: string[];
  subject: string;
  level1: string;
  level2: string;
  table: string;
  files: string[];
}
