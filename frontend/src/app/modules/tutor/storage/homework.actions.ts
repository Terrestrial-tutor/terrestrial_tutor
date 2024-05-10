import {createAction, createActionGroup, props} from '@ngrx/store';
import {Subject} from "../../../models/Subject";
import {Homework} from "../../../models/Homework";

export const saveHomework = createAction(
  '[Homework] Saving homework',
  props<{ subject: Subject }>()
)

export const saveHomeworkSuccess = createAction(
  '[Homework] Saving homework is success',
  props<{ homework: Homework }>()
)

export const saveHomeworkFailed = createAction(
  '[Homework] Saving homework is failed',
  props<{ error: Error }>()
)

export const getCurrentHomework = createAction(
  '[Homework] Get homework ID',
)

export const deleteHomework = createAction(
  '[Homework] Delete homework',
  props<{ id: number }>()
)

export const deleteHomeworkSuccess = createAction(
  '[Homework] Delete homework is success'
)

export const deleteHomeworkFailed = createAction(
  '[Homework] Delete homework is failed',
  props<{ error: Error }>()
)

export const getHomeworkFromApi = createAction(
  '[Homework] Get homework from api',
  props<{ id: number }>()
)

export const getHomeworkFromApiSuccess = createAction(
  '[Homework] Get homework from api is success',
  props<{ homework: Homework }>()
)

export const getHomeworkFromApiFailed = createAction(
  '[Homework] Get homework from api is failed',
  props<{ error: Error }>()
)

export const clearHomeworkState = createAction(
  '[Homework] Clear homework state',
)
