import React, { FunctionComponent } from "react"
import "antd/dist/antd.css"
import { Layout } from "antd"
import styles from "./TopBar.module.css"
import { useGoogleAuth } from "../GoogleAuthProvider/GoogleAuthProvider"
import { useHistory } from 'react-router-dom';

interface TopBarProps { }

const { Header } = Layout

const TopBar: FunctionComponent<TopBarProps> = () => {
  const { signOut } = useGoogleAuth()

  let history = useHistory();

  const redirect = () => {
    history.push('/projects')
  }

  return (
    <Header className={styles.TopBar}>
      <div className={styles.Logo} />
      <button onClick={redirect} className={styles.Projects}>Projects</button>
      <button className={styles.Logout} onClick={signOut}>
        Log out
      </button>
    </Header>
  )
}
export default TopBar
