import React, { useContext } from "react"
import { MenuBarContext } from "../MenuBar/MenuBar"
import BurgerButton from "../BurgerButton/BurgerButton"
import styles from "./TopBar.module.css"

const TopBar = () => {
  const { setIsShowSidebar } = useContext(MenuBarContext)
  return (
    <div className={styles.LeftSideBar__TopSection}>
      <BurgerButton onClick={() => setIsShowSidebar(true)} />
    </div>
  )
}
export default TopBar
