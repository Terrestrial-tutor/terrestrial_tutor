import {createFeatureSelector, createSelector} from "@ngrx/store";
import {Homework} from "../../../models/Homework";
import {HomeworkInitialState} from "./homework.reducer";

export const HOMEWORK_FEATURE_KEY = 'homework';

export const homeworkState =
  createFeatureSelector<HomeworkInitialState>(HOMEWORK_FEATURE_KEY);

export const getHomework = createSelector(
  homeworkState,
  (state: HomeworkInitialState) => state
)

// export const getCurrentSubject = createSelector(
//   homeworkState,
//   (state: HomeworkInitialState) => state.homeworkId
// )
