import React, { FunctionComponent, useContext } from "react"
import { BarContainerContext } from "../BarContainer/BarContainer"
import { Breadcrumb, Layout, Menu } from "antd"
import "antd/dist/antd.css"
import {
  ClockCircleOutlined,
  ContactsOutlined,
  DashboardOutlined,
  DesktopOutlined,
  ExportOutlined,
  LogoutOutlined,
  ScheduleOutlined,
  WarningOutlined,
  LinkOutlined
} from "@ant-design/icons"
import Copyright from "../../pages/Resources/Styles"
import { Link, useHistory } from "react-router-dom"
import styles from "./SideBar.module.css"
import { ProjectDetail } from "../../state/AppState"
import {
  GIT_ID_QUERY,
  GOOGLE_DRIVE_ID_QUERY,
  PROJECT_DETAIL_CONTACTS_PATH,
  PROJECT_DETAIL_EXPORT_DATA_PATH,
  PROJECT_DETAIL_GIT_PATH,
  PROJECT_DETAIL_GOOGLE_DRIVE_PATH,
  PROJECT_DETAIL_PATH,
  PROJECT_DETAIL_PROJECT_PROBLEMS_PATH,
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
  projectProblems: "menu-item-project-problems",
  exportData: "menu-item-export-data",
  timeTracking: "menu-item-time-tracking",
  contacts: "menu-item-contacts",
  logOut: "menu-item-logout"
}

const SideBar: FunctionComponent<SideBarProps> = ({ projectDetails, children }) => {
  const history = useHistory()

  const { signOut } = useGoogleAuth()

  const { isShowSidebar, setIsShowSidebar } = useContext(BarContainerContext)

  const handleOnShowProjectDetailClicked = (path: string) => {
    history.push(path)
  }

  const currentPath = window.location.pathname
  const query: URLSearchParams = useQuery()
  let defaultSelectedKey = SideBarKey.landing
  let defaultOpenKeys = ["menu-item-integration"]
  let currentPathName = "Dashboard"

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
    case PROJECT_DETAIL_PROJECT_PROBLEMS_PATH: {
      defaultSelectedKey = SideBarKey.projectProblems
      currentPathName = "Project Problems"
      break
    }
    case PROJECT_DETAIL_EXPORT_DATA_PATH: {
      defaultSelectedKey = SideBarKey.exportData
      currentPathName = "Export Data"
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
    <Layout style={{ minHeight: "100vh" }}>
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
                icon={<LinkOutlined />}
                onClick={() =>
                  handleOnShowProjectDetailClicked(`${PROJECT_DETAIL_GIT_PATH}?${PROJECT_ID_QUERY}=${projectDetails?.projectId}`)
                }
              >
                Git
              </Menu.Item>
              {projectDetails?.projectGitIntegrations &&
                projectDetails?.projectGitIntegrations.map((projectIntegration) => {
                  return (
                    <Menu.Item
                      key={`${SideBarKey.git}-${projectIntegration.integrationId}`}
                      onClick={() =>
                        handleOnShowProjectDetailClicked(
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
                onClick={() =>
                  handleOnShowProjectDetailClicked(`${PROJECT_DETAIL_TRELLO_PATH}?${PROJECT_ID_QUERY}=${projectDetails?.projectId}`)
                }
              >
                Trello
              </Menu.Item>
              {projectDetails?.projectTrelloIntegrations &&
                projectDetails?.projectTrelloIntegrations.map((projectIntegration) => {
                  return (
                    <Menu.Item
                      key={`${SideBarKey.trello}-${projectIntegration.integrationId}`}
                      onClick={() =>
                        handleOnShowProjectDetailClicked(
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
                onClick={() =>
                  handleOnShowProjectDetailClicked(`${PROJECT_DETAIL_GOOGLE_DRIVE_PATH}?${PROJECT_ID_QUERY}=${projectDetails?.projectId}`)
                }
              >
                Google Drive
              </Menu.Item>
              {projectDetails?.projectGoogleDriveIntegrations &&
                projectDetails?.projectGoogleDriveIntegrations.map((projectIntegration) => {
                  return (
                    <Menu.Item
                      key={`${SideBarKey.googleDrive}-${projectIntegration.integrationId}`}
                      onClick={() =>
                        handleOnShowProjectDetailClicked(
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
            onClick={() =>
              handleOnShowProjectDetailClicked(`${PROJECT_DETAIL_REMINDERS_PATH}?${PROJECT_ID_QUERY}=${projectDetails?.projectId}`)
            }
          >
            Reminders
          </Menu.Item>
          <Menu.Item
            key={SideBarKey.projectProblems}
            icon={<WarningOutlined />}
            onClick={() =>
              handleOnShowProjectDetailClicked(`${PROJECT_DETAIL_PROJECT_PROBLEMS_PATH}?${PROJECT_ID_QUERY}=${projectDetails?.projectId}`)
            }
          >
            Project Problems
          </Menu.Item>
          <Menu.Item
            key={SideBarKey.exportData}
            icon={<ExportOutlined />}
            onClick={() =>
              handleOnShowProjectDetailClicked(`${PROJECT_DETAIL_EXPORT_DATA_PATH}?${PROJECT_ID_QUERY}=${projectDetails?.projectId}`)
            }
          >
            Export Data
          </Menu.Item>
          <Menu.Item
            key={SideBarKey.timeTracking}
            icon={<ScheduleOutlined />}
            onClick={() =>
              handleOnShowProjectDetailClicked(`${PROJECT_DETAIL_TIME_TRACKING_PATH}?${PROJECT_ID_QUERY}=${projectDetails?.projectId}`)
            }
          >
            Time Tracking
          </Menu.Item>
          <Menu.Item
            key={SideBarKey.contacts}
            icon={<ContactsOutlined />}
            onClick={() =>
              handleOnShowProjectDetailClicked(`${PROJECT_DETAIL_CONTACTS_PATH}?${PROJECT_ID_QUERY}=${projectDetails?.projectId}`)
            }
          >
            Contacts
          </Menu.Item>
          <Menu.Item key={SideBarKey.logOut} icon={<LogoutOutlined />} onClick={signOut}>
            Logout
          </Menu.Item>
        </Menu>
      </Sider>
      <Layout className="site-layout">
        <Content style={{ margin: "10px 50px" }}>
          <Breadcrumb style={{ margin: "16px 0" }}>
            <Breadcrumb.Item>
              <Link to={{ pathname: "/projects" }}>Project List</Link>
            </Breadcrumb.Item>
            <Breadcrumb.Item>{currentPathName}</Breadcrumb.Item>
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
