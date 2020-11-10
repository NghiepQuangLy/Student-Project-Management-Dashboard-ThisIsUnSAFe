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

/**
 * This method returns the BarContainer component which is the combination of a Top Bar component and a Side Bar
 * component
 * @param shouldContainSideBar Whether the BarContainer component should include a Side Bar component
 *                             When shouldContainSideBar = true, display Top Bar and Side Bar
 *                             When shouldContainSideBar = false, display Top Bar only
 * @param projectDetails The details of the project
 * @param children The children components
 * @return The HTML for the BarContainer component
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
