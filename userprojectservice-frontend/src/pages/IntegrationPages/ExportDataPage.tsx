import React, { useLayoutEffect } from "react"
import { Page } from "../Page"
import { AppStatus } from "../../models/AppStatus"
import BarContainer from "../../components/BarContainer/BarContainer"
import { useGoogleAuth } from "../../components/GoogleAuthProvider/GoogleAuthProvider"

const ExportDataPage: Page = ({ integration, state, dispatch }) => {
  const { googleUser } = useGoogleAuth()
  const emailAddress = googleUser?.getBasicProfile()?.getEmail()

  useLayoutEffect(() => {
    if (state.projectDetailStatus === AppStatus.INITIAL && emailAddress && state.user?.projects[0].projectId) {
      // dispatch(AppAction.projectDetailLoading())
      //
      // UseCase.loadInitialProject(integration, emailAddress, state.user?.projects[0].projectId).then((project) => {
      //   dispatch(AppAction.projectDetailSuccess(project))
      // })
    }
  }, [dispatch, integration, state.projectDetailStatus, state.user, emailAddress])

  return (
    <div>
      <BarContainer shouldContainSideBar={true} pageTitle="Integrations Page">
        <div className="site-layout-background" style={{ padding: 24, minHeight: 360 }}>
          Export Data Page.
        </div>
        <iframe src="http://localhost:3000" />
      </BarContainer>
    </div>
  )
}

export default ExportDataPage
