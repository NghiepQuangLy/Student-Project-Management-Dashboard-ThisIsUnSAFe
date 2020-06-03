import React, { useLayoutEffect } from "react"
import "./ProjectList.module.css"
import { Page } from "../Page"
import * as UseCase from "../../usecase/ProjectList"
import * as AppAction from "../../state/AppAction"
import { AppStatus } from "../../models/AppStatus"

const ProjectList: Page = ({ integration, state, dispatch }) => {
  const isEmpty = state.projectListStatus === AppStatus.SUCCESS && state.projects.length === 0

  useLayoutEffect(() => {
    if (state.projectListStatus === AppStatus.INITIAL) {
      dispatch(AppAction.projectListLoading())

      UseCase.loadInitialProjectList(integration).then((details) => {
        dispatch(AppAction.projectListSuccess(details))
      })
    }

    if (state.projectStatus === AppStatus.INITIAL) {
      dispatch(AppAction.projectLoading())

      UseCase.loadInitialProject(integration).then((project) => {
        dispatch(AppAction.projectSuccess(project))
      })
    }
  }, [dispatch, integration, state.projectListStatus, state.projectStatus])

  return (
    <div>
      <div>
        {state.projectListStatus === AppStatus.LOADING ? (
          <h1>Loading</h1>
        ) : isEmpty ? (
          <h1>Empty History</h1>
        ) : (
          state.projects.map((item) => <h1>{"projectId: " + item.projectId + "|projectName:" + item.projectName}</h1>)
        )}
      </div>
      <div>
        {state.projectStatus === AppStatus.LOADING ? (
          <h1>Loading</h1>
        ) : (
          <div>
            <div>{state.currentProject?.projectId}</div>
            <div>{state.currentProject?.projectName}</div>
            <div>{state.currentProject?.projectGitIds}</div>
            <div>{state.currentProject?.projectTrelloIds}</div>
            <div>{state.currentProject?.projectGoogleDocIds}</div>
          </div>
        )}
      </div>
    </div>
  )
}

export default ProjectList
