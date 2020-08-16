import React, { useContext, useState } from "react"
import BurgerButton from "../BurgerButton/BurgerButton"
import { BarContainerContext } from "../BarContainer/BarContainer"
import SideBarItem from "./SideBarItem/SideBarItem"
import SideBarExpandableItem from "./SideBarExpandableItem/SideBarExpandableItem"
import styles from "./SideBar.module.css"

const SideBar = () => {
  const { isShowSidebar, setIsShowSidebar } = useContext(BarContainerContext)
  const [isIntegrationExpand, setIsIntegrationExpand] = useState(false)
  const sidebarStyle = isShowSidebar ? styles.SideBarShow : styles.SideBarHide
  return (
    <div className={[styles.SideBar, sidebarStyle].join(" ")}>
      <div className={styles.TopWrapper}>
        <BurgerButton onClick={() => setIsShowSidebar(false)} />
      </div>
      <ul className={styles.MenuWrapper}>
        <SideBarItem itemLink={"#"} itemName={"Dashboard"} sideBarItemStyleLevel={"first"} />

        <SideBarExpandableItem
          itemName={"Integrations"}
          isIntegrationExpand={isIntegrationExpand}
          setIsIntegrationExpand={setIsIntegrationExpand}
        >
          {isIntegrationExpand && <SideBarItem itemLink={"#"} itemName={"Git"} sideBarItemStyleLevel={"second"} />}
          {isIntegrationExpand && <SideBarItem itemLink={"#"} itemName={"Trello"} sideBarItemStyleLevel={"second"} />}
          {isIntegrationExpand && <SideBarItem itemLink={"#"} itemName={"Google Drive"} sideBarItemStyleLevel={"second"} />}
        </SideBarExpandableItem>

        <SideBarItem itemLink={"#"} itemName={"Reminders"} sideBarItemStyleLevel={"first"} />
        <SideBarItem itemLink={"#"} itemName={"Project Problems"} sideBarItemStyleLevel={"first"} />
        <SideBarItem itemLink={"#"} itemName={"Export Data"} sideBarItemStyleLevel={"first"} />
        <SideBarItem itemLink={"#"} itemName={"Time Tracking"} sideBarItemStyleLevel={"first"} />
        <SideBarItem itemLink={"#"} itemName={"Contacts"} sideBarItemStyleLevel={"first"} />
        <SideBarItem itemLink={"#"} itemName={"Sign out"} sideBarItemStyleLevel={"first"} />
      </ul>
    </div>
  )
}

export default SideBar
