import React, { Dispatch, FunctionComponent, SetStateAction, useState } from "react"
import TopBar from "../TopBar/TopBar"
import styles from "./MenuBar.module.css"

interface MenuBarProps {
  title?: string
}

interface ContextInterface {
  isShowSidebar: Boolean
  setIsShowSidebar: Dispatch<SetStateAction<boolean>>
}

export const MenuBarContext = React.createContext({} as ContextInterface)

const MenuBar: FunctionComponent<MenuBarProps> = ({ title, children }) => {
  const [isShowSidebar, setIsShowSidebar] = useState(false)
  const leftBarStyle = isShowSidebar ? styles.LeftSideBar__container__overlayShow : styles.LeftSideBar__container__overlayHide

  return (
    <MenuBarContext.Provider value={{ isShowSidebar, setIsShowSidebar }}>
      <div className={styles.LeftSideBar__container}>
        <div className={leftBarStyle} role="button" onClick={() => setIsShowSidebar(false)}></div>
        {<TopBar />}
        {/* {<LeftSection />} */}
      </div>
      <div>{title || "testTitle"}</div>
      {children}
    </MenuBarContext.Provider>
  )
}

export default MenuBar
