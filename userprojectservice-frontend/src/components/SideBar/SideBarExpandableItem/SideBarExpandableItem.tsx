import React, { Dispatch, FunctionComponent, SetStateAction } from "react"
import styles from "./SideBarExpandableItem.module.css"

interface SideBarExpandableItemProps {
  itemName: string
  isIntegrationExpand: Boolean
  setIsIntegrationExpand: Dispatch<SetStateAction<boolean>>
}

const SideBarExpandableItem: FunctionComponent<SideBarExpandableItemProps> = ({
  itemName,
  isIntegrationExpand,
  setIsIntegrationExpand,
  children
}) => {
  return (
    <div className={styles.SideBarExpandableItem}>
      <div className={styles.ExpandableButton} role="button" onClick={() => setIsIntegrationExpand(!isIntegrationExpand)}>
        {itemName}
      </div>
      {isIntegrationExpand && children}
    </div>
  )
}

export default SideBarExpandableItem
