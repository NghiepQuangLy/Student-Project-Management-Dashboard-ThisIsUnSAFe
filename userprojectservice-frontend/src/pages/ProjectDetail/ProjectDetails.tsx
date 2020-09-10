import React, { useLayoutEffect } from "react"
import "../ProjectList/ProjectList.module.css"
import { Page } from "../Page"
import * as AppAction from "../../state/AppAction"
import { AppStatus } from "../../models/AppStatus"
import { Redirect } from "react-router-dom"
import BarContainer from "../../components/BarContainer/BarContainer"
import ProjectDetailsLanding from "../../components/ProjectDetailsLanding/ProjectDetailsLanding"
import { useGoogleAuth } from "../../components/GoogleAuthProvider/GoogleAuthProvider"
import { PROJECT_DETAIL_PATH, PROJECT_ID_QUERY, useQuery } from "../../util/useQuery"
import ProjectDetailsIntegration from "../../components/ProjectDetailsIntegration/ProjectDetailsIntegration"

const ProjectDetails: Page = ({ integration, state, dispatch }) => {
  const { googleUser, isInitialized } = useGoogleAuth()
  const emailAddress = googleUser?.getBasicProfile()?.getEmail()

  const query: URLSearchParams = useQuery()
  const projectId = query?.get(PROJECT_ID_QUERY)
  const currentPath = window.location.pathname

  useLayoutEffect(() => {
    if (state.projectDetailStatus === AppStatus.INITIAL && emailAddress && projectId) {
      dispatch(AppAction.projectDetailLoading())

      integration
        .getProject(emailAddress, projectId)
        .then((project) => {
          dispatch(AppAction.projectDetailSuccess(project))
        })
        .catch(() => dispatch(AppAction.projectDetailFailure()))
    }
  }, [dispatch, integration, state.projectDetailStatus, emailAddress, projectId])

  return (
    <div>
      <BarContainer shouldContainSideBar={true} projectDetails={state.currentProject ?? undefined}>
        {!isInitialized || (!emailAddress && <Redirect to="/" />)}
        {!projectId && <Redirect to="/projects" />}
        {state.projectDetailStatus === AppStatus.INITIAL || state.projectDetailStatus === AppStatus.LOADING ? (
          <h1>Loading</h1>
        ) : state.projectDetailStatus === AppStatus.FAILURE ? (
          <h1>Something went wrong.</h1>
        ) : currentPath === PROJECT_DETAIL_PATH ? (
          <ProjectDetailsLanding state={state} />
        ) : (
          <ProjectDetailsIntegration path={currentPath} />
        )}
      </BarContainer>
    </div>
  )
}

export default ProjectDetails
