import { AppAction, AppActionType, ProjectDetailSuccessAction, UserDetailSuccessAction } from "./AppAction"
import { AppState } from "./AppState"
import { Reducer } from "react"
import { AppStatus } from "../models/AppStatus"

const AppReducer: Reducer<AppState, AppAction<AppActionType, any>> = (prevState, action): AppState => {
  switch (action.type) {
    case "PROJECT_DETAIL_LOADING": {
      return {
        ...prevState,
        projectDetailStatus: AppStatus.LOADING
      }
    }

    case "PROJECT_DETAIL_SUCCESS": {
      const projectSuccessAction = action as ProjectDetailSuccessAction

      return {
        ...prevState,
        currentProject: {
          projectId: projectSuccessAction.payload.projectId,
          projectName: projectSuccessAction.payload.projectName,
          projectGitIds: projectSuccessAction.payload.projectGitIds,
          projectGoogleDriveIds: projectSuccessAction.payload.projectGoogleDriveIds,
          projectGoogleFolderIds: projectSuccessAction.payload.projectGoogleFolderIds,
          projectTrelloIds: projectSuccessAction.payload.projectTrelloIds
        },
        projectDetailStatus: AppStatus.SUCCESS
      }
    }

    case "PROJECT_DETAIL_FAILURE": {
      return {
        ...prevState,
        projectDetailStatus: AppStatus.FAILURE
      }
    }

    case "USER_DETAIL_LOADING": {
      return {
        ...prevState,
        userDetailStatus: AppStatus.LOADING
      }
    }

    case "USER_DETAIL_SUCCESS": {
      const userSuccessAction = action as UserDetailSuccessAction

      return {
        ...prevState,
        user: {
          givenName: userSuccessAction.payload.givenName,
          familyName: userSuccessAction.payload.familyName,
          emailAddress: userSuccessAction.payload.emailAddress,
          userGroup: userSuccessAction.payload.userGroup,
          projects: userSuccessAction.payload.projects
        },
        userDetailStatus: AppStatus.SUCCESS
      }
    }

    default: {
      throw Error(`Action ${action.type} is not recognised`)
    }
  }
}

export default AppReducer
