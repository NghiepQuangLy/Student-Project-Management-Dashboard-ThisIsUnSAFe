import React, { FunctionComponent, useContext } from "react"
import { BarContainerContext } from "../BarContainer/BarContainer"
import 'antd/dist/antd.css';
import { Layout, Button } from 'antd';
import {
    MenuUnfoldOutlined,
    MenuFoldOutlined,
} from '@ant-design/icons';
import styles from "./TopBar.module.css";

interface TopBarProps {
  shouldContainSideBar: Boolean
  pageTitle: string
}

const { Header } = Layout;

const TopBar: FunctionComponent<TopBarProps> = ({ shouldContainSideBar, pageTitle }) => {
  const { isShowSidebar, setIsShowSidebar } = useContext(BarContainerContext)
  return (
        <Header className="header">
            <Button type="primary" onClick={() => { if (!isShowSidebar) {setIsShowSidebar(true)} else {setIsShowSidebar(false)}}} style={{ marginBottom: 16 }}>
                {React.createElement(isShowSidebar ? MenuUnfoldOutlined : MenuFoldOutlined)}
            </Button>
            <div className={styles.logo} />
            <div >
                {shouldContainSideBar}
            {/*<div className={styles.PageTitle}>{pageTitle}</div>*/}
            </div>
        </Header>
  )
}
export default TopBar
