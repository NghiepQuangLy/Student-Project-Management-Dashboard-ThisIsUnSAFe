import React, { FunctionComponent, useContext } from "react"
import { BarContainerContext } from "../BarContainer/BarContainer"
import BurgerButton from "../BurgerButton/BurgerButton"
import styles from "./TopBar.module.css"

interface TopBarProps {
  shouldContainSideBar: Boolean
  pageTitle: string
}

const TopBar: FunctionComponent<TopBarProps> = ({ shouldContainSideBar, pageTitle }) => {
  const { setIsShowSidebar } = useContext(BarContainerContext)
  return (
    <div className={styles.LeftSideBar__TopSection}>
      {shouldContainSideBar && <BurgerButton onClick={() => setIsShowSidebar(true)} />}
      <div className={styles.pageTitle}>{pageTitle}</div>
      <a className={styles.logout} href="#">
        Sign Out
      </a>
    </div>
  )
}
export default TopBar
