import { ProjectResponse } from "../models/ProjectResponse"
import { UserResponse } from "../models/UserResponse"
import { BurndownChartResponse } from "../models/BurndownChartResponse"

export type AppActionType =
  | "PROJECT_LIST_LOADING"
  | "PROJECT_LIST_SUCCESS"
  | "PROJECT_DETAIL_LOADING"
  | "PROJECT_DETAIL_SUCCESS"
  | "PROJECT_DETAIL_FAILURE"
  | "USER_DETAIL_LOADING"
  | "USER_DETAIL_SUCCESS"
  | "USER_DETAIL_FAILURE"
  | "TRELLO_BURNDOWN_SUCCESS"

export interface AppAction<T extends AppActionType, P> {
  type: T
  payload: P
}

export type ProjectDetailLoadingAction = AppAction<"PROJECT_DETAIL_LOADING", undefined>

export type ProjectDetailSuccessAction = AppAction<"PROJECT_DETAIL_SUCCESS", ProjectResponse>

export type ProjectDetailFailureAction = AppAction<"PROJECT_DETAIL_FAILURE", undefined>

export type UserDetailLoadingAction = AppAction<"USER_DETAIL_LOADING", undefined>

export type UserDetailSuccessAction = AppAction<"USER_DETAIL_SUCCESS", UserResponse>

export type UserDetailFailureAction = AppAction<"USER_DETAIL_FAILURE", boolean>

export type TrelloBurndownSuccessAction = AppAction<"TRELLO_BURNDOWN_SUCCESS", BurndownChartResponse>

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

export const userDetailSuccess = (user: UserResponse): UserDetailSuccessAction => ({
  type: "USER_DETAIL_SUCCESS",
  payload: user
})

export const userDetailFailure = (isEmailInvalid: boolean): UserDetailFailureAction => ({
  type: "USER_DETAIL_FAILURE",
  payload: isEmailInvalid
})

export const trelloBurndownSuccess = (burndown: BurndownChartResponse): TrelloBurndownSuccessAction => ({
  type: "TRELLO_BURNDOWN_SUCCESS",
  payload: burndown
})
