import { InitialProject, InitialUser } from "../usecase/UseCase"

export type AppActionType =
  | "PROJECT_LIST_LOADING"
  | "PROJECT_LIST_SUCCESS"
  | "PROJECT_LOADING"
  | "PROJECT_SUCCESS"
  | "USER_LOADING"
  | "USER_SUCCESS"

export interface AppAction<T extends AppActionType, P> {
  type: T
  payload: P
}

export type ProjectLoadingAction = AppAction<"PROJECT_LOADING", undefined>

export type ProjectSuccessAction = AppAction<"PROJECT_SUCCESS", InitialProject>

export type UserLoadingAction = AppAction<"USER_LOADING", undefined>

export type UserSuccessAction = AppAction<"USER_SUCCESS", InitialUser>

export const projectLoading = (): ProjectLoadingAction => ({
  type: "PROJECT_LOADING",
  payload: undefined
})

export const projectSuccess = (project: InitialProject): ProjectSuccessAction => ({
  type: "PROJECT_SUCCESS",
  payload: project
})

export const userLoading = (): UserLoadingAction => ({
  type: "USER_LOADING",
  payload: undefined
})

export const userSuccess = (user: InitialUser): UserSuccessAction => ({
  type: "USER_SUCCESS",
  payload: user
})
