import React, { Dispatch, FunctionComponent, SetStateAction, useState } from "react"
import TopBar from "../TopBar/TopBar"
import styles from "./MenuBar.module.css"
import SideBar from "../SideBar/SideBar"

interface MenuBarProps {
  title?: string
  shouldContainSideBar: Boolean
}

interface ContextInterface {
  isShowSidebar: Boolean
  setIsShowSidebar: Dispatch<SetStateAction<boolean>>
}

export const MenuBarContext = React.createContext({} as ContextInterface)

const MenuBar: FunctionComponent<MenuBarProps> = ({ title, shouldContainSideBar, children }) => {
  const [isShowSidebar, setIsShowSidebar] = useState(false)
  const menuBarStyle = isShowSidebar ? styles.LeftSideBar__container__overlayShow : styles.LeftSideBar__container__overlayHide

  return (
    <MenuBarContext.Provider value={{ isShowSidebar, setIsShowSidebar }}>
      <div className={styles.LeftSideBar__container}>
        {shouldContainSideBar && <div className={menuBarStyle} role="button" onClick={() => setIsShowSidebar(false)}></div>}
        <TopBar shouldContainSideBar={shouldContainSideBar} />
        {shouldContainSideBar && <SideBar />}
      </div>
      <div>{title || "testTitle"}</div>
      {children}
    </MenuBarContext.Provider>
  )
}

export default MenuBar
