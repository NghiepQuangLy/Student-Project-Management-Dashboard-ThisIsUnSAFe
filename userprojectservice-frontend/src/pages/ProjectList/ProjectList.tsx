import React, { useLayoutEffect } from "react"
import "./ProjectList.module.css"
import { Page } from "../Page"
import { Link, Redirect } from "react-router-dom"
import * as UseCase from "../../usecase/UseCase"
import * as AppAction from "../../state/AppAction"
import { AppStatus } from "../../models/AppStatus"

const ProjectList: Page = ({ integration, state, dispatch }) => {
  const isEmpty = state.projectListStatus === AppStatus.SUCCESS && state.user?.projects.length === 0

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
      <div>
        {state.userStatus === AppStatus.LOADING ? (
          <h1>Loading</h1>
        ) : isEmpty ? (
          <h1>Empty History</h1>
        ) : (
          <div>
            <strong>Given Name:</strong> {state.user?.givenName}
            <strong>Family Name:</strong> {state.user?.familyName}
            <strong>Email Address:</strong> {state.user?.emailAddress}
            <strong>User Group:</strong> {state.user?.userGroup}
            <strong>Projects: </strong>
          </div>
        )}
      </div>
      <div>
        {state.projectListStatus === AppStatus.LOADING ? (
          <h1>Loading</h1>
        ) : isEmpty ? (
          <h1>Empty History</h1>
        ) : (
          state.user &&
          state.user.projects.map((item) => (
            <Link key="item.projectId" to={{ pathname: "/project", state: { from: "item.projectId" } }}>
              {"projectId: " + item.projectId + "|projectName:" + item.projectName}
              <br />
            </Link>
          ))
        )}
      </div>
    </div>
  )
}

export default ProjectList
