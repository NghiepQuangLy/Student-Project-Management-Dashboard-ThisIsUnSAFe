import React, { useLayoutEffect } from "react"
import "./ProjectList.module.css"
import { Page } from "../Page"
import * as UseCase from "../../usecase/UseCase"
import * as AppAction from "../../state/AppAction"
import { AppStatus } from "../../models/AppStatus"
import { Redirect } from "react-router-dom"

const ProjectDetails: Page = ({ integration, state, dispatch }) => {
  useLayoutEffect(() => {
    if (state.projectStatus === AppStatus.INITIAL) {
      dispatch(AppAction.projectLoading())

      state.user?.emailAddress &&
        state.user?.projects[0].projectId &&
        UseCase.loadInitialProject(integration, state.user?.emailAddress, state.user?.projects[0].projectId).then((project) => {
          dispatch(AppAction.projectSuccess(project))
        })
    }
  }, [dispatch, integration, state.projectListStatus, state.projectStatus, state.user])

  return (
    <div>
      {!state.user?.emailAddress && <Redirect to="/" />}
      {state.projectStatus === AppStatus.LOADING ? (
        <h1>Loading</h1>
      ) : (
        <div>
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
