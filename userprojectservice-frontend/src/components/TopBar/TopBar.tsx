import React, { FunctionComponent } from "react"
import "antd/dist/antd.css"
import { Layout } from "antd"
import styles from "./TopBar.module.css"

interface TopBarProps {}

const { Header } = Layout

const TopBar: FunctionComponent<TopBarProps> = ({}) => {
  return (
    <Header className="header">
      <div className={styles.logo} />
    </Header>
  )
}
export default TopBar
