import { AppAction, AppActionType, ProjectSuccessAction, UserSuccessAction } from "./AppAction"
import { AppState } from "./AppState"
import { Reducer } from "react"
import { AppStatus } from "../models/AppStatus"

const AppReducer: Reducer<AppState, AppAction<AppActionType, any>> = (prevState, action): AppState => {
  switch (action.type) {
    case "PROJECT_LOADING": {
      return {
        ...prevState,
        projectStatus: AppStatus.LOADING
      }
    }

    case "PROJECT_SUCCESS": {
      const projectSuccessAction = action as ProjectSuccessAction

      return {
        ...prevState,
        currentProject: {
          projectId: projectSuccessAction.payload.projectId,
          projectName: projectSuccessAction.payload.projectName,
          projectGitIds: projectSuccessAction.payload.projectGitIds,
          projectGoogleDocIds: projectSuccessAction.payload.projectGoogleDocIds,
          projectTrelloIds: projectSuccessAction.payload.projectTrelloIds
        },
        projectStatus: AppStatus.SUCCESS
      }
    }

    case "USER_LOADING": {
      return {
        ...prevState,
        userStatus: AppStatus.LOADING
      }
    }

    case "USER_SUCCESS": {
      const userSuccessAction = action as UserSuccessAction

      return {
        ...prevState,
        user: {
          givenName: userSuccessAction.payload.givenName,
          familyName: userSuccessAction.payload.familyName,
          emailAddress: userSuccessAction.payload.emailAddress,
          userGroup: userSuccessAction.payload.userGroup,
          projects: userSuccessAction.payload.projects
        },
        userStatus: AppStatus.SUCCESS
      }
    }

    default: {
      throw Error(`Action ${action.type} is not recognised`)
    }
  }
}

export default AppReducer
