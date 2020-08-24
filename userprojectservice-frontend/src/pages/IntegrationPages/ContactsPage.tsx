import React, { useLayoutEffect } from "react"
import { Page } from "../Page"
import * as UseCase from "../../usecase/UseCase"
import * as AppAction from "../../state/AppAction"
import { AppStatus } from "../../models/AppStatus"
import BarContainer from "../../components/BarContainer/BarContainer"
import { useGoogleAuth } from "../../components/GoogleAuthProvider/GoogleAuthProvider"

const ContactsPage: Page = ({ integration, state, dispatch }) => {
  const { googleUser } = useGoogleAuth()
  const emailAddress = googleUser?.getBasicProfile()?.getEmail()

  useLayoutEffect(() => {
    if (state.projectDetailStatus === AppStatus.INITIAL && emailAddress && state.user?.projects[0].projectId) {
      dispatch(AppAction.projectDetailLoading())

      UseCase.loadInitialProject(integration, emailAddress, state.user?.projects[0].projectId).then((project) => {
        dispatch(AppAction.projectDetailSuccess(project))
      })
    }
  }, [dispatch, integration, state.projectDetailStatus, state.user])

  return (
    <div>
      <BarContainer shouldContainSideBar={true} pageTitle="Integrations Page">
        <div className="site-layout-background">Contacts Page.</div>
        <iframe src="http://localhost:3000" width="1450" height="800" />
      </BarContainer>
    </div>
  )
}

export default ContactsPage
