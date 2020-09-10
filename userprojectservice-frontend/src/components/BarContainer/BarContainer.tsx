import React, { Dispatch, FunctionComponent, SetStateAction, useState } from "react"
import TopBar from "../TopBar/TopBar"
// import styles from "./BarContainer.module.css"
import SideBar from "../SideBar/SideBar"
import { Layout } from "antd"
import { ProjectDetail } from "../../state/AppState"

interface BarContainerProps {
  shouldContainSideBar: Boolean
  projectDetails?: ProjectDetail
}

interface ContextInterface {
  isShowSidebar: boolean
  setIsShowSidebar: Dispatch<SetStateAction<boolean>>
}

export const BarContainerContext = React.createContext({} as ContextInterface)

const BarContainer: FunctionComponent<BarContainerProps> = ({ shouldContainSideBar, projectDetails, children }) => {
  const [isShowSidebar, setIsShowSidebar] = useState(false)

  return (
    <BarContainerContext.Provider value={{ isShowSidebar, setIsShowSidebar }}>
      <Layout>
        <TopBar />
        {shouldContainSideBar && <SideBar projectDetails={projectDetails}>{children}</SideBar>}
      </Layout>
    </BarContainerContext.Provider>
  )
}

export default BarContainer
