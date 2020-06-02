import { AppAction, AppActionType, ProjectListSuccessAction } from "./AppAction"
import { AppState } from "./AppState"
import { Reducer } from "react"
import { ProjectListStatus } from "../models/ProjectListStatus"

const AppReducer: Reducer<AppState, AppAction<AppActionType, any>> = (prevState, action): AppState => {
  switch (action.type) {
    case "PROJECT_LIST_LOADING": {
      return {
        ...prevState,
        projectListStatus: ProjectListStatus.LOADING
      }
    }

    case "PROJECT_LIST_SUCCESS": {
      const projectListSuccessAction = action as ProjectListSuccessAction

      return {
        ...prevState,
        projects: projectListSuccessAction.payload.projects,
        projectListStatus: ProjectListStatus.SUCCESS
      }
    }

    default: {
      throw Error(`Action ${action.type} is not recognised`)
    }
  }
}

export default AppReducer
