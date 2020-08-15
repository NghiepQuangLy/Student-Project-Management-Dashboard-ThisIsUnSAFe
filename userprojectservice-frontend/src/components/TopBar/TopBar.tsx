import React, { FunctionComponent, useContext } from "react"
import { BarContainerContext } from "../BarContainer/BarContainer"
import BurgerButton from "../BurgerButton/BurgerButton"
import styles from "./TopBar.module.css"

interface TopBarProps {
  shouldContainSideBar: Boolean
}

const TopBar: FunctionComponent<TopBarProps> = ({ shouldContainSideBar }) => {
  const { setIsShowSidebar } = useContext(BarContainerContext)
  return (
    <div className={styles.LeftSideBar__TopSection}>{shouldContainSideBar && <BurgerButton onClick={() => setIsShowSidebar(true)} />}</div>
  )
}
export default TopBar
