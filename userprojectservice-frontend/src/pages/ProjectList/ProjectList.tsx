import React, { useLayoutEffect } from "react"
import { Page } from "../Page"
import { Redirect } from "react-router-dom"
import * as AppAction from "../../state/AppAction"
import { AppStatus } from "../../models/AppStatus"
import BarContainer from "../../components/BarContainer/BarContainer"
import { useGoogleAuth } from "../../components/GoogleAuthProvider/GoogleAuthProvider"
import Loading from "../../components/Loading/Loading"
import ProjectListLanding from "../../components/ProjectListLanding/ProjectListLanding"

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
  // const history = useHistory()
  // const [expandedItems, setExpandedItems] = useState([])
  // const onNodeToggle = (event: any, nodeId: any) => {
  //   setExpandedItems(nodeId)
  // }
  // const [projectList, setProjectList] = useState<Project[] | undefined>([])
  //
  // const isEmpty = state.userDetailStatus === AppStatus.SUCCESS && state.user?.projects.length === 0

  const { googleUser, isInitialized, isSignedIn } = useGoogleAuth()
  const emailAddress = googleUser?.getBasicProfile()?.getEmail()

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
          }

          console.log(e)
          console.log(response)
          console.log(response.status)
        })
    }
  }, [dispatch, integration, state.userDetailStatus, state.user, isInitialized, emailAddress])

  return (
    <div>
      {!isInitialized ? (
        <Loading iconColor={"black"} />
      ) : isSignedIn ? (
        emailAddress ? (
          <BarContainer shouldContainSideBar={false}>
            <ProjectListLanding state={state} />
          </BarContainer>
        ) : (
          <h1>something went wrong</h1>
        )
      ) : (
        <Redirect to="/" />
      )}
    </div>
  )
}

export default ProjectList
