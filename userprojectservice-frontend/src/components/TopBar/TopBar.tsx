import React, { FunctionComponent } from "react"
import "antd/dist/antd.css"
import { Layout } from "antd"
import styles from "./TopBar.module.css"
import { useGoogleAuth } from "../GoogleAuthProvider/GoogleAuthProvider"
import { useHistory } from "react-router-dom"

interface TopBarProps {}

/*
Top Bar Component
 */
const TopBar: FunctionComponent<TopBarProps> = () => {
  const { Header } = Layout
  const { signOut } = useGoogleAuth()
  let history = useHistory()

  return (
    <Header className={styles.TopBar}>
      <div className={styles.Logo} />
      <button onClick={() => history.push("/example")} className={styles.SAFeAndAgileFeatures}>
        SAFe & Agile Features
      </button>
      <button onClick={() => history.push("/projects")} className={styles.Projects}>
        Projects
      </button>
      <button className={styles.Logout} onClick={signOut}>
        Log out
      </button>
    </Header>
  )
}
export default TopBar
