import React, { useLayoutEffect } from "react"
import { Page } from "../Page"
import { AppStatus } from "../../models/AppStatus"
import BarContainer from "../../components/BarContainer/BarContainer"
import { useGoogleAuth } from "../../components/GoogleAuthProvider/GoogleAuthProvider"
import styles from "./Integration.module.css"

const IntegrationPage: Page = ({ integration, state, dispatch }) => {
  const { googleUser } = useGoogleAuth()
  const emailAddress = googleUser?.getBasicProfile()?.getEmail()

  useLayoutEffect(() => {
    if (state.projectDetailStatus === AppStatus.INITIAL && emailAddress && state.user?.projects[0].projectId) {
      // dispatch(AppAction.projectDetailLoading())
      // UseCase.loadInitialProject(integration, emailAddress, state.user?.projects[0].projectId).then((project) => {
      //   dispatch(AppAction.projectDetailSuccess(project))
      // })
    }
  }, [dispatch, integration, state.projectDetailStatus, state.user, emailAddress])

  return (
    <div>
      <BarContainer shouldContainSideBar={true}>
        {/*<div className="site-layout-background" style={{ padding: 24, minHeight: 360 }}>*/}
        {/*  Bill is a cat.*/}
        {/*</div>*/}
        <iframe className={styles.Iframe} src="http://localhost:3000/Example" scrolling={"no"} />
      </BarContainer>
    </div>
  )
}

export default IntegrationPage
