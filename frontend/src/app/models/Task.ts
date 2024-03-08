import {Subject} from "./Subject";

export interface Task {
  id: number;
  name: string;
  taskText: string;
  answer: string;
  subject: string;
  level1: string;
  level2: string;
  homeworks: string[];
}
