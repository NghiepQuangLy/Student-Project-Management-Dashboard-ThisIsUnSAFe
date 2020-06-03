import React, { useLayoutEffect } from "react"
import "./ProjectList.module.css"
import { Page } from "../Page"
import * as UseCase from "../../usecase/ProjectList"
import * as AppAction from "../../state/AppAction"
import { AppStatus } from "../../models/AppStatus"

const ProjectDetails: Page = ({ integration, state, dispatch }) => {

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
        {state.projectStatus === AppStatus.LOADING ? (
          <h1>Loading</h1>
        ) : (
          <div>
            {console.log(state)}
            <div>projectId: {state.currentProject?.projectId}</div>
            <div>projectName: {state.currentProject?.projectName}</div>
            <div>projectGitIds: {state.currentProject?.projectGitIds.map((item) => item + ", ")}</div>
            <div>projectTrelloIds: {state.currentProject?.projectTrelloIds.map((item) => item + ", ")}</div>
            <div>projectGoogleDocIds: {state.currentProject?.projectGoogleDocIds.map((item) => item + ", ")}</div>
          </div>
        )}
      </div>
  )
}

export default ProjectDetails
