import React, { FunctionComponent } from "react"
import TopBar from "../TopBar/TopBar"
import styles from "./BarContainer.module.css"
import SideBar from "../SideBar/SideBar"
import { Layout } from "antd"
import { ProjectDetail } from "../../state/AppState"

interface BarContainerProps {
  shouldContainSideBar: Boolean
  projectDetails?: ProjectDetail
}

/*
 Top Bar and Side Bar component,
 when shouldContainSideBar = true, display top bar and side bar,
 when shouldContainSideBar = false, display top bar only
*/
const BarContainer: FunctionComponent<BarContainerProps> = ({ shouldContainSideBar, projectDetails, children }) => {
  return (
    <Layout>
      <TopBar />
      <div className={styles.BottomContainer}>
        {shouldContainSideBar ? <SideBar projectDetails={projectDetails}>{children}</SideBar> : <div>{children}</div>}
      </div>
    </Layout>
  )
}

export default BarContainer
