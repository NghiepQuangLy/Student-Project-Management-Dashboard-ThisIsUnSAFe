import React, { useLayoutEffect } from "react"
import "./ProjectList.module.css"
import { Page } from "../Page"
import { Link } from "react-router-dom"
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
          state.projects.map((item) => <Link key="item.projectId" to={{ pathname: "/project", state: { from: "item.projectId" } }}>
          {"projectId: " + item.projectId + "|projectName:" + item.projectName}
          <br/></Link>
          )
        )}
      </div>
    </div>
  )
}

export default ProjectList