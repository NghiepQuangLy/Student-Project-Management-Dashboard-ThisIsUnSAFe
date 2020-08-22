import React, { Dispatch, FunctionComponent, SetStateAction, useState } from "react"
import TopBar from "../TopBar/TopBar"
// import styles from "./BarContainer.module.css"
import SideBar from "../SideBar/SideBar"
import { Layout } from "antd"

interface BarContainerProps {
  shouldContainSideBar: Boolean
  pageTitle: string
  project?: string
}

interface ContextInterface {
  isShowSidebar: boolean
  setIsShowSidebar: Dispatch<SetStateAction<boolean>>
}

export const BarContainerContext = React.createContext({} as ContextInterface)

const BarContainer: FunctionComponent<BarContainerProps> = ({ shouldContainSideBar, pageTitle, project, children }) => {
  const [isShowSidebar, setIsShowSidebar] = useState(false)

  return (
    <BarContainerContext.Provider value={{ isShowSidebar, setIsShowSidebar }}>
      <Layout>
        <TopBar />
        {shouldContainSideBar && <SideBar project={project}>{children}</SideBar>}
      </Layout>
    </BarContainerContext.Provider>
  )
}

export default BarContainer
