import React, { FunctionComponent } from "react"
import styles from "./BurgerButton.module.css"

interface BurgerButtonProps {
  onClick: any
}

const BurgerButton: FunctionComponent<BurgerButtonProps> = ({ onClick }) => {
  return (
    <div className={styles.LeftSideBar__BurgerButton} role="button" onClick={onClick}>
      <i></i>
      <i></i>
      <i></i>
    </div>
  )
}
export default BurgerButton
