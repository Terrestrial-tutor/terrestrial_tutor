import { Homework } from "./Homework";

export interface Pupil {
  id: number;
  balance: number;
  homeworks: Homework[];
  price: number;
  subjects: string[];
  tutors: string[];
  username: string;
  name: string;
  surname: string;
  patronymic: string;
}
