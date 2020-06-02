import React, { useLayoutEffect } from "react"
import "./ProjectList.module.css"
import { Page } from "../Page"
import * as CartContainerUseCase from "../../usecase/ProjectList"
import * as AppAction from "../../state/AppAction"
import { ProjectListStatus } from "../../models/ProjectListStatus"

const ProjectList: Page = ({ integration, state, dispatch }) => {
  const isEmpty = state.projectListStatus === ProjectListStatus.SUCCESS && state.projects.length === 0

  useLayoutEffect(() => {
    if (state.projectListStatus === ProjectListStatus.INITIAL) {
      dispatch(AppAction.projectListLoading())

      CartContainerUseCase.loadInitialProjectList(integration).then((details) => {
        dispatch(AppAction.projectListSuccess(details))
      })
    }
  }, [dispatch, integration, state.projectListStatus])

  return (
    <div>
      {state.projectListStatus === ProjectListStatus.LOADING ? (
        <h1>Loading</h1>
      ) : isEmpty ? (
        <h1>Empty History</h1>
      ) : (
        state.projects.map((item) => <h1>{"projectId: " + item.projectId + "|projectName:" + item.projectName}</h1>)
      )}
    </div>
  )
}

export default ProjectList
