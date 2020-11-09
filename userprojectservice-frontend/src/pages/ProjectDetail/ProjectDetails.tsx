import React, { useLayoutEffect } from "react"
import "../ProjectList/ProjectList.module.css"
import { Page } from "../Page"
import * as AppAction from "../../state/AppAction"
import { AppStatus } from "../../models/AppStatus"
import { Redirect } from "react-router-dom"
import BarContainer from "../../components/BarContainer/BarContainer"
import { useGoogleAuth } from "../../components/GoogleAuthProvider/GoogleAuthProvider"
import { PROJECT_DETAIL_PATH, PROJECT_ID_QUERY, useQuery } from "../../util/useQuery"
import ProjectDetailsIntegration from "../../components/ProjectDetailsIntegration/ProjectDetailsIntegration"
import Loading from "../../components/Loading/Loading"
import styles from "./ProjectDetails.module.css"
import Dashboard from "../../components/Dashboard/Dashboard"

/** This method returns the Project Details component which displays either the Dashboard page or if the url is for an
 * Integration the Project Integration page.
 * @param integration Allows API calls to be used in function
 * @param state The react state of the application
 * @param dispatch The react dispatch of the application
 * @return The HTML for the ProjectDetails
 */
const ProjectDetails: Page = ({ integration, state, dispatch }) => {
  const { googleUser, isSignedIn, isInitialized } = useGoogleAuth()
  const emailAddress = googleUser?.getBasicProfile()?.getEmail()

  const query: URLSearchParams = useQuery()
  const projectId = query?.get(PROJECT_ID_QUERY)
  const currentPath = window.location.pathname

  // useLayoutEffect will execute before return
  useLayoutEffect(() => {
    const shouldGetProject =
      state.projectDetailStatus === AppStatus.INITIAL ||
      (state.projectDetailStatus === AppStatus.SUCCESS && state.currentProject?.projectId !== projectId)

    // Call APIs
    if (emailAddress && projectId && shouldGetProject) {
      dispatch(AppAction.projectDetailLoading())
      console.log("Id-token: " + googleUser?.getAuthResponse().id_token)
      // Get Project API
      integration
        .getProject(emailAddress, projectId, googleUser?.getAuthResponse().id_token ?? "")
        .then((project) => {
          dispatch(AppAction.projectDetailSuccess(project))

          // Get Burndown Chart API
          console.log(project.projectTrelloIntegration[0].integrationId)
          integration
            .getBurndownChart(
              project.projectTrelloIntegration[0].integrationId,
              "d8989708bc9f936bec339c8ecd88ab019e682da9c4f9fcb90f95701a689aa46f"
            )
            .then((project) => {
              dispatch(AppAction.trelloBurndownSuccess(project))
            })
            .catch(() => console.log("Trello Burndown Chart Endpoint not working"))
        })
        .catch(() => dispatch(AppAction.projectDetailFailure()))
    }
  }, [dispatch, integration, state.projectDetailStatus, emailAddress, projectId, state.currentProject])

  const isLoading =
    !isInitialized ||
    (isInitialized &&
      isSignedIn &&
      emailAddress &&
      projectId &&
      (state.projectDetailStatus === AppStatus.INITIAL || state.projectDetailStatus === AppStatus.LOADING))
  const isRedirectToLogin = !isLoading && !isSignedIn
  const isRedirectToProjectList = !isLoading && isSignedIn && !projectId
  const isErr = !isLoading && (state.projectDetailStatus === AppStatus.FAILURE || (isSignedIn && !emailAddress))
  const isSucceed = state.projectDetailStatus === AppStatus.SUCCESS

  // Load the Dashboard or Project Detail Integration Page
  return (
    <div>
      {isLoading && (
        <BarContainer shouldContainSideBar={false}>
          <div className={styles.Loading}>
            <Loading iconColor={"black"} />
          </div>
        </BarContainer>
      )}
      {isRedirectToLogin && <Redirect to="/" />}
      {isRedirectToProjectList && <Redirect to="/projects" />}
      {isErr && (
        <BarContainer shouldContainSideBar={false}>
          <h1>Something went wrong.</h1>
        </BarContainer>
      )}
      {isSucceed && (
        <BarContainer shouldContainSideBar={true} projectDetails={state.currentProject ?? undefined}>
          {currentPath === PROJECT_DETAIL_PATH ? (
            <Dashboard projectDetails={state.currentProject ?? undefined} />
          ) : (
            <ProjectDetailsIntegration />
          )}
        </BarContainer>
      )}
    </div>
  )
}

export default ProjectDetails
