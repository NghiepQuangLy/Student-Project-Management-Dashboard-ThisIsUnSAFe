import React, { FunctionComponent, useState } from "react"
import { Breadcrumb, Layout, Menu } from "antd"
import "antd/dist/antd.css"
import {
  ClockCircleOutlined,
  ContactsOutlined,
  DashboardOutlined,
  DesktopOutlined,
  LinkOutlined,
  LogoutOutlined,
  ScheduleOutlined
} from "@ant-design/icons"
import Copyright from "../../pages/Resources/Styles"
import { Link, useHistory } from "react-router-dom"
import styles from "./SideBar.module.css"
import { ProjectDetail } from "../../state/AppState"
import {
  GIT_ID_QUERY,
  GOOGLE_DRIVE_ID_QUERY,
  PROJECT_DETAIL_CONTACTS_PATH,
  PROJECT_DETAIL_GIT_PATH,
  PROJECT_DETAIL_GOOGLE_DRIVE_PATH,
  PROJECT_DETAIL_PATH,
  PROJECT_DETAIL_REMINDERS_PATH,
  PROJECT_DETAIL_TIME_TRACKING_PATH,
  PROJECT_DETAIL_TRELLO_PATH,
  PROJECT_ID_QUERY,
  TRELLO_ID_QUERY,
  useQuery
} from "../../util/useQuery"
import { useGoogleAuth } from "../GoogleAuthProvider/GoogleAuthProvider"

const { SubMenu } = Menu
const { Sider, Content, Footer } = Layout

interface SideBarProps {
  projectDetails?: ProjectDetail
}

const SideBarKey = {
  landing: "menu-item-landing",
  linkGit: "sub-menu-git-item-link-new",
  git: "sub-menu-git-item",
  linkTrello: "sub-menu-trello-item-link-new",
  trello: "sub-menu-trello-item",
  linkGoogleDrive: "sub-menu-google-drive-item-link-new",
  googleDrive: "sub-menu-google-drive-item",
  reminder: "menu-item-reminders",
  timeTracking: "menu-item-time-tracking",
  contacts: "menu-item-contacts",
  logOut: "menu-item-logout"
}

/*
Side Bar and Content Component:
Display content according to current url path
 */
const SideBar: FunctionComponent<SideBarProps> = ({ projectDetails, children }) => {
  const history = useHistory()
  const query: URLSearchParams = useQuery()
  const [isShowSidebar, setIsShowSidebar] = useState(false)

  const { signOut } = useGoogleAuth()
  const currentPath = window.location.pathname

  // default value
  let defaultSelectedKey = SideBarKey.landing
  let defaultOpenKeys = ["menu-item-integration"]
  let currentPathName = "Dashboard"

  // get current integration base on url path
  switch (currentPath) {
    case PROJECT_DETAIL_GIT_PATH: {
      const id = query?.get(GIT_ID_QUERY)
      defaultSelectedKey = id ? `${SideBarKey.git}-${id}` : SideBarKey.linkGit
      defaultOpenKeys.push("sub-menu-git")
      currentPathName = "Git"
      break
    }
    case PROJECT_DETAIL_TRELLO_PATH: {
      const id = query?.get(TRELLO_ID_QUERY)
      defaultSelectedKey = id ? `${SideBarKey.trello}-${id}` : SideBarKey.linkTrello
      defaultOpenKeys.push("sub-menu-trello")
      currentPathName = "Trello"
      break
    }
    case PROJECT_DETAIL_GOOGLE_DRIVE_PATH: {
      const id = query?.get(GOOGLE_DRIVE_ID_QUERY)
      defaultSelectedKey = id ? `${SideBarKey.googleDrive}-${id}` : SideBarKey.linkGoogleDrive
      defaultOpenKeys.push("sub-menu-googledrive")
      currentPathName = "Google Drive"
      break
    }
    case PROJECT_DETAIL_REMINDERS_PATH: {
      defaultSelectedKey = SideBarKey.reminder
      currentPathName = "Reminders"
      break
    }
    case PROJECT_DETAIL_TIME_TRACKING_PATH: {
      defaultSelectedKey = SideBarKey.timeTracking
      currentPathName = "Time Tracking"
      break
    }
    case PROJECT_DETAIL_CONTACTS_PATH: {
      defaultSelectedKey = SideBarKey.contacts
      currentPathName = "Contacts"
      break
    }
    default: {
      break
    }
  }

  return (
    <Layout className={styles.SideBar}>
      <Sider collapsible collapsed={isShowSidebar} onCollapse={setIsShowSidebar}>
        <Menu defaultSelectedKeys={[defaultSelectedKey]} defaultOpenKeys={defaultOpenKeys} mode="inline" theme="dark">
          <Menu.Item
            key={SideBarKey.landing}
            icon={<DashboardOutlined />}
            onClick={() => history.push(`${PROJECT_DETAIL_PATH}?${PROJECT_ID_QUERY}=${projectDetails?.projectId}`)}
          >
            Dashboard
          </Menu.Item>
          <SubMenu key="menu-item-integration" icon={<DesktopOutlined />} title="Integrations">
            <SubMenu key="sub-menu-git" title="Git">
              <Menu.Item
                key={SideBarKey.linkGit}
                icon={<LinkOutlined />}
                onClick={() => history.push(`${PROJECT_DETAIL_GIT_PATH}?${PROJECT_ID_QUERY}=${projectDetails?.projectId}`)}
              >
                Git
              </Menu.Item>
              {projectDetails?.projectGitIntegrations &&
                projectDetails?.projectGitIntegrations.map((projectIntegration) => {
                  return (
                    <Menu.Item
                      key={`${SideBarKey.git}-${projectIntegration.integrationId}`}
                      onClick={() =>
                        history.push(
                          `${PROJECT_DETAIL_GIT_PATH}?${PROJECT_ID_QUERY}=${projectDetails?.projectId}&${GIT_ID_QUERY}=${projectIntegration.integrationId}`
                        )
                      }
                    >
                      {projectIntegration.integrationName}
                    </Menu.Item>
                  )
                })}
            </SubMenu>
            <SubMenu key="sub-menu-trello" title="Trello">
              <Menu.Item
                key={SideBarKey.linkTrello}
                icon={<LinkOutlined />}
                onClick={() => history.push(`${PROJECT_DETAIL_TRELLO_PATH}?${PROJECT_ID_QUERY}=${projectDetails?.projectId}`)}
              >
                Trello
              </Menu.Item>
              {projectDetails?.projectTrelloIntegrations &&
                projectDetails?.projectTrelloIntegrations.map((projectIntegration) => {
                  return (
                    <Menu.Item
                      key={`${SideBarKey.trello}-${projectIntegration.integrationId}`}
                      onClick={() =>
                        history.push(
                          `${PROJECT_DETAIL_TRELLO_PATH}?${PROJECT_ID_QUERY}=${projectDetails?.projectId}&${TRELLO_ID_QUERY}=${projectIntegration.integrationId}`
                        )
                      }
                    >
                      {projectIntegration.integrationName}
                    </Menu.Item>
                  )
                })}
            </SubMenu>
            <SubMenu key="sub-menu-googledrive" title="Google Drive">
              <Menu.Item
                key={SideBarKey.linkGoogleDrive}
                icon={<LinkOutlined />}
                onClick={() => history.push(`${PROJECT_DETAIL_GOOGLE_DRIVE_PATH}?${PROJECT_ID_QUERY}=${projectDetails?.projectId}`)}
              >
                Google Drive
              </Menu.Item>
              {projectDetails?.projectGoogleDriveIntegrations &&
                projectDetails?.projectGoogleDriveIntegrations.map((projectIntegration) => {
                  return (
                    <Menu.Item
                      key={`${SideBarKey.googleDrive}-${projectIntegration.integrationId}`}
                      onClick={() =>
                        history.push(
                          `${PROJECT_DETAIL_GOOGLE_DRIVE_PATH}?${PROJECT_ID_QUERY}=${projectDetails?.projectId}&${GOOGLE_DRIVE_ID_QUERY}=${projectIntegration.integrationId}`
                        )
                      }
                    >
                      {projectIntegration.integrationName}
                    </Menu.Item>
                  )
                })}
            </SubMenu>
          </SubMenu>
          <Menu.Item
            key={SideBarKey.reminder}
            icon={<ClockCircleOutlined />}
            onClick={() => history.push(`${PROJECT_DETAIL_REMINDERS_PATH}?${PROJECT_ID_QUERY}=${projectDetails?.projectId}`)}
          >
            Reminders
          </Menu.Item>
          <Menu.Item
            key={SideBarKey.timeTracking}
            icon={<ScheduleOutlined />}
            onClick={() => history.push(`${PROJECT_DETAIL_TIME_TRACKING_PATH}?${PROJECT_ID_QUERY}=${projectDetails?.projectId}`)}
          >
            Time Tracking
          </Menu.Item>
          <Menu.Item
            key={SideBarKey.contacts}
            icon={<ContactsOutlined />}
            onClick={() => history.push(`${PROJECT_DETAIL_CONTACTS_PATH}?${PROJECT_ID_QUERY}=${projectDetails?.projectId}`)}
          >
            Contacts
          </Menu.Item>
          <Menu.Item key={SideBarKey.logOut} icon={<LogoutOutlined />} onClick={signOut}>
            Logout
          </Menu.Item>
        </Menu>
      </Sider>
      <Layout className="site-layout">
        <Content className={styles.Content}>
          <Breadcrumb>
            <Breadcrumb.Item>
              <Link to={{ pathname: "/projects" }}>Project List</Link>
            </Breadcrumb.Item>
            <Breadcrumb.Item>{currentPathName}</Breadcrumb.Item>
          </Breadcrumb>
          {children}
        </Content>
        <Footer className={styles.Content}>
          <Copyright />
        </Footer>
      </Layout>
    </Layout>
  )
}

export default SideBar
