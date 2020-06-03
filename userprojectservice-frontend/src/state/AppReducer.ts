import { AppAction, AppActionType, ProjectListSuccessAction, ProjectSuccessAction } from "./AppAction"
import { AppState } from "./AppState"
import { Reducer } from "react"
import { AppStatus } from "../models/AppStatus"

const AppReducer: Reducer<AppState, AppAction<AppActionType, any>> = (prevState, action): AppState => {
  switch (action.type) {
    case "PROJECT_LIST_LOADING": {
      return {
        ...prevState,
        projectListStatus: AppStatus.LOADING
      }
    }

    case "PROJECT_LIST_SUCCESS": {
      const projectListSuccessAction = action as ProjectListSuccessAction

      return {
        ...prevState,
        projects: projectListSuccessAction.payload.projects,
        projectListStatus: AppStatus.SUCCESS
      }
    }

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

    default: {
      throw Error(`Action ${action.type} is not recognised`)
    }
  }
}

export default AppReducer
