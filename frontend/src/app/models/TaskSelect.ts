import {Task} from "./Task";

export class TaskSelect{
  isSelected: boolean;
  task:Task;

  constructor(task: Task, isSelected: boolean = false){
    this.task = task;
    this.isSelected = isSelected;
  }
}
