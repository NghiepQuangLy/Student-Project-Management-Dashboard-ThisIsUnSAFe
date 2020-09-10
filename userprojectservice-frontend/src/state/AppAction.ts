import { InitialUser } from "../usecase/UseCase"
import { ProjectResponse } from "../models/ProjectResponse"

export type AppActionType =
  | "PROJECT_LIST_LOADING"
  | "PROJECT_LIST_SUCCESS"
  | "PROJECT_DETAIL_LOADING"
  | "PROJECT_DETAIL_SUCCESS"
  | "PROJECT_DETAIL_FAILURE"
  | "USER_DETAIL_LOADING"
  | "USER_DETAIL_SUCCESS"

export interface AppAction<T extends AppActionType, P> {
  type: T
  payload: P
}

export type ProjectDetailLoadingAction = AppAction<"PROJECT_DETAIL_LOADING", undefined>

export type ProjectDetailSuccessAction = AppAction<"PROJECT_DETAIL_SUCCESS", ProjectResponse>

export type ProjectDetailFailureAction = AppAction<"PROJECT_DETAIL_FAILURE", undefined>

export type UserDetailLoadingAction = AppAction<"USER_DETAIL_LOADING", undefined>

export type UserDetailSuccessAction = AppAction<"USER_DETAIL_SUCCESS", InitialUser>

export const projectDetailLoading = (): ProjectDetailLoadingAction => ({
  type: "PROJECT_DETAIL_LOADING",
  payload: undefined
})

export const projectDetailSuccess = (project: ProjectResponse): ProjectDetailSuccessAction => ({
  type: "PROJECT_DETAIL_SUCCESS",
  payload: project
})

export const projectDetailFailure = (): ProjectDetailFailureAction => ({
  type: "PROJECT_DETAIL_FAILURE",
  payload: undefined
})

export const userDetailLoading = (): UserDetailLoadingAction => ({
  type: "USER_DETAIL_LOADING",
  payload: undefined
})

export const userDetailSuccess = (user: InitialUser): UserDetailSuccessAction => ({
  type: "USER_DETAIL_SUCCESS",
  payload: user
})
