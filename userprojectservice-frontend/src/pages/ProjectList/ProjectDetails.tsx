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

  function linkIntegration(integrate: string, param: string) {
    let projectid = param
    if (integrate === "Git") {
      window.location.href = "/gitPage?project_id=" + projectid
    }
    else if (integrate === "Trello") {
      window.location.href = "/trelloPage?project_id=" + projectid
    }
    else if (integrate === "Google Docs") {
      window.location.href = "/googleDocPage?project_id=" + projectid
    }

  }

  function viewIntegration(integrate: string, param1: string, param2: string) {
    let projectid = param1
    let intid = param2
    if (integrate === "Git") {
      window.location.href = "/gitPage?project_id=" + projectid + "&intid=" + intid
    }
    else if (integrate === "Trello") {
      window.location.href = "/trelloPage?project_id=" + projectid + "&intid=" + intid
    }
    else if (integrate === "Google Docs") {
      window.location.href = "/googleDocPage?iproject_id=" + projectid + "&intid=" + intid
    }

  }

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
            <button type="button" onClick={() => linkIntegration("Git", state.currentProject!.projectId!)}>Git Link</button>
            <br></br>
            <button type="button" onClick={() => viewIntegration("Git", state.currentProject!.projectId!, state.currentProject!.projectGitIds![0])}>Git View</button>
            <div>projectTrelloIds: {state.currentProject?.projectTrelloIds.map((item) => item + ", ")}</div>
            <button type="button" onClick={() => linkIntegration("Trello", state.currentProject!.projectId!)}>Trello Link</button>
            <br></br>
            <button type="button" onClick={() => viewIntegration("Trello", state.currentProject!.projectId!, state.currentProject!.projectTrelloIds![0])}>Trello View</button>
            <div>projectGoogleDocIds: {state.currentProject?.projectGoogleDocIds.map((item) => item + ", ")}</div>
            <button type="button" onClick={() => linkIntegration("Google Docs", state.currentProject!.projectId!)}>Google Docs Link</button>
            <br></br>
            <button type="button" onClick={() => viewIntegration("Google Docs", state.currentProject!.projectId!, state.currentProject!.projectGoogleDocIds![0])}>Google Docs View</button>
          </div>
        )}
    </div>
  )
}

export default ProjectDetails
