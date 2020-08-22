import React, { FunctionComponent } from "react"
import "antd/dist/antd.css"
import { Layout } from "antd"
import styles from "./TopBar.module.css"
import { useGoogleAuth } from "../GoogleAuthProvider/GoogleAuthProvider"

interface TopBarProps {}

const { Header } = Layout

const TopBar: FunctionComponent<TopBarProps> = ({}) => {
  const { signOut } = useGoogleAuth()
  return (
    <Header className={styles.TopBar}>
      <div className={styles.Logo} />
      <button className={styles.Logout} onClick={signOut}>
        Log Out
      </button>
    </Header>
  )
}
export default TopBar
