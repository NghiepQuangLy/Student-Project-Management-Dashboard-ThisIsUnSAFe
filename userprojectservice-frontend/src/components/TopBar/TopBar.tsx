import React, { FunctionComponent, useContext } from "react"
import { MenuBarContext } from "../MenuBar/MenuBar"
import BurgerButton from "../BurgerButton/BurgerButton"
import styles from "./TopBar.module.css"

interface TopBarProps {
  shouldContainSideBar: Boolean
}

const TopBar: FunctionComponent<TopBarProps> = ({ shouldContainSideBar }) => {
  const { setIsShowSidebar } = useContext(MenuBarContext)
  return (
    <div className={styles.LeftSideBar__TopSection}>
      {shouldContainSideBar && <BurgerButton onClick={() => setIsShowSidebar(true)} />}
    </div>
  )
}
export default TopBar
