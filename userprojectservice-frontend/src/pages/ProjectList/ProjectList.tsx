import React, { useLayoutEffect, useState } from "react"
import { Page } from "../Page"
import { Redirect } from "react-router-dom"
import * as AppAction from "../../state/AppAction"
import { AppStatus } from "../../models/AppStatus"
import BarContainer from "../../components/BarContainer/BarContainer"
import { useGoogleAuth } from "../../components/GoogleAuthProvider/GoogleAuthProvider"
import Loading from "../../components/Loading/Loading"
import ProjectListLanding from "../../components/ProjectListLanding/ProjectListLanding"
import styles from "../ProjectDetail/ProjectDetails.module.css"
// @ts-ignore
import TrelloClient, { Trello } from "react-trello-client"

// Provide Dictionary of any data type (T) interface
export interface Dictionary<T> {
  [id: string]: T
}

// Provide ProjectDictionary interface
export interface ProjectDictionary {
  id: Dictionary<ProjectDictionary>
  key: string
  data?: ProjectData[] | undefined
}

// Provide ProjectData interface
export interface ProjectData {
  projectId: string
  projectKey: string
  projectName: string
}

/** This method returns the Project List component which displays the Project List landing page.
 * @param integration Allows API calls to be used in function
 * @param state The react state of the application
 * @param dispatch The react dispatch of the application
 * @return The HTML for the ProjectDetails
 */
const ProjectList: Page = ({ integration, state, dispatch }) => {
  const { googleUser, isInitialized, isSignedIn } = useGoogleAuth()

  const emailAddress = googleUser?.getBasicProfile()?.getEmail()
  const givenName = googleUser?.getBasicProfile()?.getGivenName()
  const familyName = googleUser?.getBasicProfile()?.getFamilyName()

  // useLayoutEffect will execute before return
  useLayoutEffect(() => {
    // Call APIs
    if (state.userDetailStatus === AppStatus.INITIAL && emailAddress) {
      dispatch(AppAction.userDetailLoading())

      // Get User API
      integration
        .getUser(emailAddress)
        .then((user) => {
          // if name not match, update user
          if (user.firstName !== givenName || user.lastName !== familyName) {
            givenName &&
              familyName &&
              integration.updateUser(emailAddress, givenName, familyName).then(() => {
                // update user successfully
                user.firstName = givenName
                user.lastName = familyName
                dispatch(AppAction.userDetailSuccess(user))
              })
          }
          // get user successfully
          dispatch(AppAction.userDetailSuccess(user))
        })
        .catch((e) => {
          const response = e as Response

          if (response.status === 400) {
            // email is not Monash email
            dispatch(AppAction.userDetailFailure(true))
          } else if (response.status === 404) {
            // user not found
            givenName &&
              familyName &&
              integration
                .createUser(emailAddress, givenName, familyName)
                .then((user) => {
                  // create user successfully
                  dispatch(AppAction.userDetailSuccess(user))
                })
                .catch(() => dispatch(AppAction.userDetailFailure(false))) //something went wrong
          } else {
            //something went wrong
            dispatch(AppAction.userDetailFailure(false))
          }
        })
    }
  }, [dispatch, integration, state.userDetailStatus, state.user, isInitialized, emailAddress, givenName, familyName])

  const [trelloToken, setToken] = useState(undefined)
  /* Auth with Trello */
  // const isAuthed = Trello.authorized && Trello.authorized()
  /* Authenticate if we need to */
  if (!trelloToken) {
    console.log("Authenticating Trello")
    return (
      <TrelloClient
        apiKey="38e2c9e0bd5f083ac3e8e19ed8a1a5fa" // Get the API key from https://trello.com/app-key/
        clientVersion={1} // number: {1}, {2}, {3}
        apiEndpoint="https://api.trello.com" // string: "https://api.trello.com"
        authEndpoint="https://trello.com" // string: "https://trello.com"
        intentEndpoint="https://trello.com" // string: "https://trello.com"
        authorizeName="React Trello Client" // string: "React Trello Client"
        authorizeType="popup" // string: popup | redirect
        authorizePersist={true}
        authorizeInteractive={false}
        authorizeScopeRead={true} // boolean: {true} | {false}
        authorizeScopeWrite={true} // boolean: {true} | {false}
        authorizeScopeAccount={true} // boolean: {true} | {false}
        authorizeExpiration="never" // string: "1hour", "1day", "30days" | "never"
        authorizeOnSuccess={() => {
          console.log("Authenticated Trello")
          setToken(Trello.token())
          console.log("Trello Token: " + Trello.token())
        }}
        authorizeOnError={() => {
          console.log("Failed to auth Trello")
        }}
        autoAuthorize={true} // boolean: {true} | {false}
        authorizeButton={true} // boolean: {true} | {false}
        buttonStyle="flat" // string: "metamorph" | "flat"
        buttonColor="grayish-blue" // string: "green" | "grayish-blue" | "light"
        buttonText="Authenticate" // string: "Login with Trello"
      />
    )
  }

  const isLoading =
    !isInitialized ||
    (isInitialized &&
      isSignedIn &&
      emailAddress &&
      (state.userDetailStatus === AppStatus.INITIAL || state.userDetailStatus === AppStatus.LOADING)) ||
    !trelloToken
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
