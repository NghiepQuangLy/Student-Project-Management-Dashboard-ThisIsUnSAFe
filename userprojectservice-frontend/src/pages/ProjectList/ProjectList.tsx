import React, { useLayoutEffect } from "react"
import { Page } from "../Page"
import { Redirect } from "react-router-dom"
import * as AppAction from "../../state/AppAction"
import { AppStatus } from "../../models/AppStatus"
import BarContainer from "../../components/BarContainer/BarContainer"
import { useGoogleAuth } from "../../components/GoogleAuthProvider/GoogleAuthProvider"
import Loading from "../../components/Loading/Loading"
import ProjectListLanding from "../../components/ProjectListLanding/ProjectListLanding"
import styles from "../ProjectDetail/ProjectDetails.module.css"

export interface Dictionary<T> {
  [id: string]: T
}

export interface ProjectDictionary {
  id: Dictionary<ProjectDictionary>
  key: string
  data?: ProjectData[] | undefined
}

export interface ProjectData {
  projectId: string
  projectKey: string
  projectName: string
}

const ProjectList: Page = ({ integration, state, dispatch }) => {
  const { googleUser, isInitialized, isSignedIn } = useGoogleAuth()
  const emailAddress = googleUser?.getBasicProfile()?.getEmail()
  const givenName = googleUser?.getBasicProfile()?.getGivenName()
  const familyName = googleUser?.getBasicProfile()?.getFamilyName()

  useLayoutEffect(() => {
    if (state.userDetailStatus === AppStatus.INITIAL && emailAddress) {
      dispatch(AppAction.userDetailLoading())

      integration
        .getUser(emailAddress)
        .then((user) => {
          dispatch(AppAction.userDetailSuccess(user))
        })
        .catch((e) => {
          const response = e as Response

          // bad request means email is not Monash email
          if (response.status === 400) {
            dispatch(AppAction.userDetailFailure(true))
          } else if (response.status === 404) {
            givenName &&
              familyName &&
              integration
                .createUser(emailAddress, givenName, familyName)
                .then((user) => {
                  dispatch(AppAction.userDetailSuccess(user))
                })
                .catch(() => dispatch(AppAction.userDetailFailure(false)))
          } else {
            dispatch(AppAction.userDetailFailure(false))
          }
        })
    }
  }, [dispatch, integration, state.userDetailStatus, state.user, isInitialized, emailAddress, givenName, familyName])

  const isLoading =
    !isInitialized ||
    (isInitialized &&
      isSignedIn &&
      emailAddress &&
      (state.userDetailStatus === AppStatus.INITIAL || state.userDetailStatus === AppStatus.LOADING))
  const isRedirectToLogin = !isLoading && !isSignedIn
  const isEmailInvalid = state.userDetailStatus === AppStatus.FAILURE && state.isUserEmailInvalid
  const isErr = !isEmailInvalid && !isLoading && (state.userDetailStatus === AppStatus.FAILURE || (isSignedIn && !emailAddress))
  const isSucceed = state.userDetailStatus === AppStatus.SUCCESS

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
      {isEmailInvalid && (
        <BarContainer shouldContainSideBar={false}>
          <h1>Please use Monash email to login.</h1>
        </BarContainer>
      )}
      {isErr && (
        <BarContainer shouldContainSideBar={false}>
          <h1>Something went wrong.</h1>
        </BarContainer>
      )}
      {isSucceed && (
        <BarContainer shouldContainSideBar={false}>
          <ProjectListLanding state={state} />
        </BarContainer>
      )}
    </div>
  )
}

export default ProjectList
