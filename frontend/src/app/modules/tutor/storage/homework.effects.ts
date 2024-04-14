import {Actions, createEffect, ofType} from "@ngrx/effects";
import {inject} from "@angular/core";
import {TutorService} from "../services/tutor.service";
import * as HomeworkActions from "./homework.actions";
import {map, of, Subject, switchMap} from "rxjs";
import {catchError} from "rxjs/operators";
import {Homework} from "../../../models/Homework";

export const homeworkCreateEffect = createEffect(
  () => {
    const actions = inject(Actions);
    let apiService = inject(TutorService);
    return actions.pipe(
      ofType(HomeworkActions.saveHomework),
      switchMap(
        ({subject}) => apiService.createHomework(subject).pipe(
          map((homework: Homework) => HomeworkActions.saveHomeworkSuccess({homework})),
          catchError((error) => {
            console.log(error);
            return of(HomeworkActions.saveHomeworkFailed({error}));
          })
        )
      )
    )
  }, {functional: true}
)

export const deleteHomework = createEffect(
  () => {
    const actions = inject(Actions);
    let apiService = inject(TutorService);
    return actions.pipe(
      ofType(HomeworkActions.deleteHomework),
      switchMap(
        ({id}) => apiService.deleteHomeworkById(id).pipe(
          map(() => HomeworkActions.deleteHomeworkSuccess()),
          catchError((error) => {
            console.log(error);
            return of(HomeworkActions.deleteHomeworkFailed({error}));
          })
        )
      )
    )
  }, {functional: true}
)

export const getHomeworkFromApi = createEffect(
  () => {
    const actions = inject(Actions);
    let apiService = inject(TutorService);
    return actions.pipe(
      ofType(HomeworkActions.getHomeworkFromApi),
      switchMap(
        ({id}) => apiService.getHomework(id).pipe(
          map((homework: Homework) => HomeworkActions.getHomeworkFromApiSuccess({homework})),
          catchError((error) => {
            console.log(error);
            return of(HomeworkActions.getHomeworkFromApiFailed({error}));
          })
        )
      )
    )
  }, {functional: true}
)
