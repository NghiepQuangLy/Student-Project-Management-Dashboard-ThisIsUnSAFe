import React, { FunctionComponent, useContext } from "react"
import BurgerButton from "../BurgerButton/BurgerButton"
import { BarContainerContext } from "../BarContainer/BarContainer"
import styles from "./SideBar.module.css"

interface SideBarItemProps {
    itemName: string
    itemLink: string
}

const SideBarItem: FunctionComponent<SideBarItemProps> = ({ itemName, itemLink }) => {
    return (
        <li>
            <a href={itemLink}>{itemName}</a>
        </li>
    )
}

const SideBar = () => {
    const { isShowSidebar, setIsShowSidebar } = useContext(BarContainerContext)
    const sidebarStyle = isShowSidebar ? styles.SideBarShow : styles.SideBarHide
    return (
        <div className={[styles.SideBar, sidebarStyle].join(" ")}>
            <div className={styles.TopWrapper}>
                <BurgerButton onClick={() => setIsShowSidebar(false)} />
            </div>
            <ul className={styles.MenuWrapper}>
                <SideBarItem itemLink={"#"} itemName={"Dashboard"} />
                <SideBarItem itemLink={"#"} itemName={"Integrations"} />
                <SideBarItem itemLink={"#"} itemName={"Reminders"} />
                <SideBarItem itemLink={"#"} itemName={"Project Problems"} />
                <SideBarItem itemLink={"#"} itemName={"Export Data"} />
                <SideBarItem itemLink={"#"} itemName={"Time Tracking"} />
                <SideBarItem itemLink={"#"} itemName={"Contacts"} />
                <SideBarItem itemLink={"#"} itemName={"Sign out"} />
            </ul>
        </div>
    )
}

export default SideBar
