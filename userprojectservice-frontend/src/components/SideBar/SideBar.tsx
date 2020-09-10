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
import { Link, useHistory } from "react-router-dom"
import styles from "./SideBar.module.css"
import { ProjectDetail } from "../../state/AppState"
import {
  GIT_ID_QUERY,
  GOOGLE_DRIVE_ID_QUERY,
  PROJECT_DETAIL_GIT_PATH,
  PROJECT_DETAIL_GOOGLE_DRIVE_PATH,
  PROJECT_DETAIL_PATH,
  PROJECT_DETAIL_TRELLO_PATH,
  PROJECT_ID_QUERY,
  TRELLO_ID_QUERY,
  useQuery
} from "../../util/useQuery"

const { SubMenu } = Menu
const { Sider, Content, Footer } = Layout
const clientId = "12178522373-e5nmdu6ogip7e70f2sn645j30n55fgke.apps.googleusercontent.com"

interface SideBarProps {
  projectDetails?: ProjectDetail
}

const SideBarKey = {
  landing: "menu-item-landing",
  linkGit: "sub-menu-git-item-link-new",
  git: "sub-menu-git-item",
  linkTrello: "sub-menu-trello-item-link-new",
  trello: "sub-menu-trello-item",
  linkGoogleDrive: "sub-menu-googledrive-item-link-new",
  googleDrive: "sub-menu-googledrive-item"
}

const SideBar: FunctionComponent<SideBarProps> = ({ projectDetails, children }) => {
  const history = useHistory()

  const onLogoutSuccess = () => {
    history.push("/")
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

  const handleOnShowProjectDetailClicked = (path: string) => {
    history.push(path)
  }

  const currentPath = window.location.pathname
  const query: URLSearchParams = useQuery()
  let defaultSelectedKey = SideBarKey.landing
  let defaultOpenKeys = ["menu-item-integration"]

  switch (currentPath) {
    case PROJECT_DETAIL_GIT_PATH: {
      const id = query?.get(GIT_ID_QUERY)
      defaultSelectedKey = id ? `${SideBarKey.git}-${id}` : SideBarKey.linkGit
      defaultOpenKeys.push("sub-menu-git")
      break
    }
    case PROJECT_DETAIL_TRELLO_PATH: {
      const id = query?.get(TRELLO_ID_QUERY)
      defaultSelectedKey = id ? `${SideBarKey.trello}-${id}` : SideBarKey.linkTrello
      defaultOpenKeys.push("sub-menu-trello")
      break
    }
    case PROJECT_DETAIL_GOOGLE_DRIVE_PATH: {
      const id = query?.get(GOOGLE_DRIVE_ID_QUERY)
      defaultSelectedKey = id ? `${SideBarKey.googleDrive}-${id}` : SideBarKey.linkGoogleDrive
      defaultOpenKeys.push("sub-menu-googledrive")
      break
    }
    default: {
      break
    }
  }

  return (
    <Layout style={{ minHeight: "100vh", marginTop: "64px" }}>
      <Sider collapsible collapsed={isShowSidebar} onCollapse={setIsShowSidebar} className={styles.SideBar}>
        <Menu defaultSelectedKeys={[defaultSelectedKey]} defaultOpenKeys={defaultOpenKeys} mode="inline" theme="dark">
          <Menu.Item
            key={SideBarKey.landing}
            icon={<DashboardOutlined />}
            onClick={() => handleOnShowProjectDetailClicked(`${PROJECT_DETAIL_PATH}?${PROJECT_ID_QUERY}=${projectDetails?.projectId}`)}
          >
            Dashboard
          </Menu.Item>
          <SubMenu key="menu-item-integration" icon={<DesktopOutlined />} title="Integrations">
            <SubMenu key="sub-menu-git" title="Git">
              <Menu.Item
                key={SideBarKey.linkGit}
                onClick={() =>
                  handleOnShowProjectDetailClicked(`${PROJECT_DETAIL_GIT_PATH}?${PROJECT_ID_QUERY}=${projectDetails?.projectId}`)
                }
              >
                + Link new
              </Menu.Item>
              {projectDetails?.projectGitIds &&
                projectDetails?.projectGitIds.map((id) => {
                  return (
                    <Menu.Item
                      key={`${SideBarKey.git}-${id}`}
                      onClick={() =>
                        handleOnShowProjectDetailClicked(
                          `${PROJECT_DETAIL_GIT_PATH}?${PROJECT_ID_QUERY}=${projectDetails?.projectId}&${GIT_ID_QUERY}=${id}`
                        )
                      }
                    >
                      {id}
                    </Menu.Item>
                  )
                })}
            </SubMenu>
            <SubMenu key="sub-menu-trello" title="Trello">
              <Menu.Item
                key={SideBarKey.linkTrello}
                onClick={() =>
                  handleOnShowProjectDetailClicked(`${PROJECT_DETAIL_TRELLO_PATH}?${PROJECT_ID_QUERY}=${projectDetails?.projectId}`)
                }
              >
                + Link new
              </Menu.Item>
              {projectDetails?.projectTrelloIds &&
                projectDetails?.projectTrelloIds.map((id) => {
                  return (
                    <Menu.Item
                      key={`${SideBarKey.linkTrello}-${id}`}
                      onClick={() =>
                        handleOnShowProjectDetailClicked(
                          `${PROJECT_DETAIL_TRELLO_PATH}?${PROJECT_ID_QUERY}=${projectDetails?.projectId}&${TRELLO_ID_QUERY}=${id}`
                        )
                      }
                    >
                      {id}
                    </Menu.Item>
                  )
                })}
            </SubMenu>
            <SubMenu key="sub-menu-googledrive" title="Google Drive">
              <Menu.Item
                key={SideBarKey.linkGoogleDrive}
                onClick={() =>
                  handleOnShowProjectDetailClicked(`${PROJECT_DETAIL_GOOGLE_DRIVE_PATH}?${PROJECT_ID_QUERY}=${projectDetails?.projectId}`)
                }
              >
                + Link new
              </Menu.Item>
              {projectDetails?.projectGoogleDriveIds &&
                projectDetails?.projectGoogleDriveIds.map((id) => {
                  return (
                    <Menu.Item
                      key={`${SideBarKey.googleDrive}-${id}`}
                      onClick={() =>
                        handleOnShowProjectDetailClicked(
                          `${PROJECT_DETAIL_GOOGLE_DRIVE_PATH}?${PROJECT_ID_QUERY}=${projectDetails?.projectId}&${GOOGLE_DRIVE_ID_QUERY}=${id}`
                        )
                      }
                    >
                      {id}
                    </Menu.Item>
                  )
                })}
            </SubMenu>
          </SubMenu>
          <Menu.Item key="14" icon={<StarOutlined />}>
            <Link to={{ pathname: "/all-events" }}>All Events</Link>
          </Menu.Item>
          <Menu.Item key="8" icon={<ClockCircleOutlined />}>
            Reminders
          </Menu.Item>
          <Menu.Item key="9" icon={<WarningOutlined />}>
            Project Problems
          </Menu.Item>
          <Menu.Item key="10" icon={<ExportOutlined />}>
            <Link to={{ pathname: "/export-data" }}>Export Data</Link>
          </Menu.Item>
          <Menu.Item key="11" icon={<ScheduleOutlined />}>
            <Link to={{ pathname: "/time-tracking" }}>Time Tracking</Link>
          </Menu.Item>
          <Menu.Item key="12" icon={<ContactsOutlined />}>
            <Link to={{ pathname: "/contacts" }}>Contacts</Link>
          </Menu.Item>
          <Menu.Item key="13" icon={<LogoutOutlined />} onClick={signOut}>
            Logout
          </Menu.Item>
        </Menu>
      </Sider>
      <Layout className="site-layout">
        <Content style={{ margin: "10px 50px" }}>
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
