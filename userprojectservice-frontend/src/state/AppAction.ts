import { InitialProjectList } from "../usecase/ProjectList"

export type AppActionType = "PROJECT_LIST_LOADING" | "PROJECT_LIST_SUCCESS"

export interface AppAction<T extends AppActionType, P> {
  type: T
  payload: P
}

export type ProjectListLoadingAction = AppAction<"PROJECT_LIST_LOADING", undefined>

export type ProjectListSuccessAction = AppAction<"PROJECT_LIST_SUCCESS", InitialProjectList>

export const projectListLoading = (): ProjectListLoadingAction => ({
  type: "PROJECT_LIST_LOADING",
  payload: undefined
})

export const projectListSuccess = (details: InitialProjectList): ProjectListSuccessAction => ({
  type: "PROJECT_LIST_SUCCESS",
  payload: details
})
