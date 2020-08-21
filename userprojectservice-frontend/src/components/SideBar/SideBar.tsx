import React, { FunctionComponent, useContext } from "react"
import { BarContainerContext } from "../BarContainer/BarContainer"
import { Breadcrumb, Layout, Menu } from "antd"
import "antd/dist/antd.css"
import {
  DashboardOutlined,
  ClockCircleOutlined,
  DesktopOutlined,
  WarningOutlined,
  ExportOutlined,
  ScheduleOutlined,
  ContactsOutlined,
  LogoutOutlined,
  StarOutlined
} from "@ant-design/icons"
import { useGoogleLogout } from "react-google-login"
import Copyright from "../../pages/Resources/Styles"
import { Link } from "react-router-dom"

const { SubMenu } = Menu
const { Sider, Content, Footer } = Layout
const clientId = "12178522373-e5nmdu6ogip7e70f2sn645j30n55fgke.apps.googleusercontent.com"

interface SideBarProps {}

const SideBar: FunctionComponent<SideBarProps> = ({ children }) => {
  const onLogoutSuccess = () => {
    window.location.href = "/"
  }
  const onFailure = () => {
    console.log("Logout failed")
  }

  const { signOut } = useGoogleLogout({
    clientId,
    onLogoutSuccess,
    onFailure
  })

  const { isShowSidebar, setIsShowSidebar } = useContext(BarContainerContext)

  return (
    <Layout style={{ minHeight: "100vh" }}>
      <Sider collapsible collapsed={isShowSidebar} onCollapse={setIsShowSidebar}>
        <Menu defaultSelectedKeys={["1"]} defaultOpenKeys={["sub1"]} mode="inline" theme="dark">
          <Menu.Item key="1" icon={<DashboardOutlined />}>
            <a href={"/project"}>Dashboard</a>
          </Menu.Item>
          <SubMenu key="sub1" icon={<DesktopOutlined />} title="Integrations">
            <Menu.Item key="sub1-1">
              <a href={"/integration"}>Git</a>
            </Menu.Item>
            <SubMenu key="sub2" title="Trello">
              <Menu.Item key="sub2-1">Trello Link 1</Menu.Item>
              <Menu.Item key="sub2-2">Trello Link 2</Menu.Item>
            </SubMenu>
            <Menu.Item key="7">
              <a href={"/integration"}>Google Drive</a>
            </Menu.Item>
          </SubMenu>
          <Menu.Item key="14" icon={<StarOutlined />}>
            All Events
          </Menu.Item>
          <Menu.Item key="8" icon={<ClockCircleOutlined />}>
            Reminders
          </Menu.Item>
          <Menu.Item key="9" icon={<WarningOutlined />}>
            Project Problems
          </Menu.Item>
          <Menu.Item key="10" icon={<ExportOutlined />}>
            Export Data
          </Menu.Item>
          <Menu.Item key="11" icon={<ScheduleOutlined />}>
            Time Tracking
          </Menu.Item>
          <Menu.Item key="12" icon={<ContactsOutlined />}>
            Contacts
          </Menu.Item>
          <Menu.Item key="13" icon={<LogoutOutlined />} onClick={signOut}>
            Logout
          </Menu.Item>
        </Menu>
      </Sider>
      <Layout className="site-layout">
        <Content style={{ margin: "0 16px" }}>
          <Breadcrumb style={{ margin: "16px 0" }}>
            <Breadcrumb.Item>
              <Link to={{ pathname: "/projects" }}>Project list</Link>
            </Breadcrumb.Item>
            <Breadcrumb.Item>Bill</Breadcrumb.Item>
          </Breadcrumb>
          {children}
        </Content>
        <Footer style={{ textAlign: "center" }}>
          <Copyright />
        </Footer>
      </Layout>
    </Layout>
  )
}

export default SideBar
