import React, { Dispatch, FunctionComponent, SetStateAction, useState } from "react"
import TopBar from "../TopBar/TopBar"
import styles from "./BarContainer.module.css"
import SideBar from "../SideBar/SideBar"

interface BarContainerProps {
  shouldContainSideBar: Boolean
  pageTitle: string
}

interface ContextInterface {
  isShowSidebar: boolean
  setIsShowSidebar: Dispatch<SetStateAction<boolean>>
}

export const BarContainerContext = React.createContext({} as ContextInterface)

const BarContainer: FunctionComponent<BarContainerProps> = ({ shouldContainSideBar, pageTitle, children }) => {
  const [isShowSidebar, setIsShowSidebar] = useState(false)

  return (
    <BarContainerContext.Provider value={{ isShowSidebar, setIsShowSidebar }}>
      <div className={styles.BarContainer}>
        <TopBar />
        {shouldContainSideBar && <SideBar>{children}</SideBar>}
      </div>
    </BarContainerContext.Provider>
  )
}

export default BarContainer
