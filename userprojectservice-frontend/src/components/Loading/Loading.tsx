import React, { FunctionComponent } from "react"
import styles from "./Loading.module.css"

interface LoadingProps {
  iconColor: "white" | "black"
}
/**
 * This method returns the Loading animation component (rotating wheel animation)
 * reference: https://loading.io/css/
 * @param iconColor The color of the animation icon
 * @return The HTML for the Loading component
 */
const Loading: FunctionComponent<LoadingProps> = ({ iconColor }) => {
  // icon color
  const iconStyle = [styles.Loading, iconColor === "white" ? styles.LoadingWhite : styles.LoadingBlack].join(" ")

  return (
    <div className={iconStyle}>
      <div></div>
      <div></div>
      <div></div>
      <div></div>
      <div></div>
      <div></div>
      <div></div>
      <div></div>
      <div></div>
      <div></div>
      <div></div>
      <div></div>
    </div>
  )
}
export default Loading
