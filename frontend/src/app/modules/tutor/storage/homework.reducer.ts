import {Action, createFeature, createReducer, on} from "@ngrx/store";
import * as HomeworkActions from "./homework.actions";
import {Homework} from "../../../models/Homework";
import {state} from "@angular/animations";

export const HOMEWORK_FEATURE_KEY = 'homework';

export interface HomeworkInitialState {
  homework: Homework | null;
  state: string;
}

export const initialState: HomeworkInitialState = {
  homework: null,
  state: 'init'
}

export const homeworkFeature = createFeature({
  name: HOMEWORK_FEATURE_KEY,
  reducer: createReducer(
    initialState,
    on(HomeworkActions.saveHomeworkSuccess, (state, {homework}) => ({...state,
      homework: homework,
      state: "saved"
    })),
    on(HomeworkActions.saveHomeworkFailed, (state, {error}) => ({...state,
      homework: null,
      state: error.message
    })),
    on(HomeworkActions.getCurrentHomework, state => ({...state})),
    on(HomeworkActions.clearHomeworkState, state => ({...state = initialState})),
    on(HomeworkActions.deleteHomeworkSuccess, state => ({...state = initialState})),
    on(HomeworkActions.deleteHomeworkFailed, (state, {error}) => ({...state,
      homework: null,
      state: error.message
    })),
    on(HomeworkActions.getHomeworkFromApiSuccess, (state, {homework}) =>
      ({...state,
        homework: homework,
        state: 'get'
      })),
    on(HomeworkActions.deleteHomeworkFailed, (state, {error}) => ({...state,
      homework: null,
      state: error.message
    })),
  )
})

// export const subjectFeature = createFeature({
//     name: SUBJECT_FEATURE_KEY,
//     reducer: createReducer(
//       initialState,
//       on(SubjectActions.setCurrentSubject, (state, {subject}) => ({
//         subject: subject,
//         status: "contain"
//       })),
//       on(SubjectActions.getCurrentSubject, state => ({...state})),
//     )
// });
