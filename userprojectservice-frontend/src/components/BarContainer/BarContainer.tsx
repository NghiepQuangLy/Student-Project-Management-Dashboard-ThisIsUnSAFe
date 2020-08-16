import React, { Dispatch, FunctionComponent, SetStateAction, useState } from "react"
import TopBar from "../TopBar/TopBar"
import styles from "./BarContainer.module.css"
import SideBar from "../SideBar/SideBar"

interface BarContainerProps {
  title?: string
  shouldContainSideBar: Boolean
  pageTitle: string
}

interface ContextInterface {
  isShowSidebar: Boolean
  setIsShowSidebar: Dispatch<SetStateAction<boolean>>
}

export const BarContainerContext = React.createContext({} as ContextInterface)

const BarContainer: FunctionComponent<BarContainerProps> = ({ title, shouldContainSideBar, pageTitle, children }) => {
  const [isShowSidebar, setIsShowSidebar] = useState(false)

  return (
    <BarContainerContext.Provider value={{ isShowSidebar, setIsShowSidebar }}>
      <div className={styles.BarContainer}>
        <TopBar shouldContainSideBar={shouldContainSideBar} pageTitle={pageTitle} />
        {shouldContainSideBar && <SideBar />}
      </div>
      <div>{title || "testTitle"}</div>
      {children}
    </BarContainerContext.Provider>
  )
}

export default BarContainer
