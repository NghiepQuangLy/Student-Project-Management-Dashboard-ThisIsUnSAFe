import React, { FunctionComponent } from "react"
import styles from "./SideBarItem.module.css"

interface SideBarItemProps {
  itemName: string
  itemLink: string
  sideBarItemStyleLevel: "first" | "second"
}

const Level = {
  first: styles.FirstLevel,
  second: styles.SecondLevel
}

const SideBarItem: FunctionComponent<SideBarItemProps> = ({ itemName, itemLink, sideBarItemStyleLevel }) => {
  const sideBarItemLink = [styles.SideBarItemLink, Level[sideBarItemStyleLevel]].join(" ")

  return (
    <div className={styles.SideBarItem}>
      <li>
        <a className={sideBarItemLink} href={itemLink}>
          {itemName}
        </a>
      </li>
    </div>
  )
}

export default SideBarItem
