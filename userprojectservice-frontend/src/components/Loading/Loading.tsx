import React, { FunctionComponent } from "react"
import styles from "./Loading.module.css"

interface LoadingProps {
  iconColor: "white" | "black"
}
/*
Loading animation component
reference: https://loading.io/css/
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
