import React from "react"
//import BurgerButton from "../BurgerButton/BurgerButton"
//import { BarContainerContext } from "../BarContainer/BarContainer"
// import SideBarItem from "./SideBarItem/SideBarItem"
// import SideBarExpandableItem from "./SideBarExpandableItem/SideBarExpandableItem"
// import styles from "./SideBar.module.css"
import { Layout, Menu } from "antd";
import 'antd/dist/antd.css';
import {
  DashboardOutlined,
  ClockCircleOutlined,
  DesktopOutlined,
    WarningOutlined,
    ExportOutlined,
    ScheduleOutlined,
    ContactsOutlined,
    LogoutOutlined
} from '@ant-design/icons';
import { useGoogleLogout } from "react-google-login";


const { SubMenu } = Menu;
const { Sider } = Layout;
const clientId = "12178522373-e5nmdu6ogip7e70f2sn645j30n55fgke.apps.googleusercontent.com";


const SideBar = () => {
    const onLogoutSuccess = () => {
        window.location.href = "www.google.com"
    }
    const onFailure = () => {
        console.log("Logout failed")
    }

    const { signOut } = useGoogleLogout({
        clientId,
        onLogoutSuccess,
        onFailure,
    })

  //const { isShowSidebar, setIsShowSidebar } = useContext(BarContainerContext)
  //const [isIntegrationExpand, setIsIntegrationExpand] = useState(false)
  //const sidebarStyle = isShowSidebar ? styles.SideBarShow : styles.SideBarHide
  return (
        <Layout>
          <Sider>
            <Menu
                defaultSelectedKeys={['1']}
                defaultOpenKeys={['sub1']}
                mode="inline"
                theme="dark"

            >
              <Menu.Item key="1" icon={<DashboardOutlined />}>
                Dashboard
              </Menu.Item>
              <SubMenu key="sub1" icon={<DesktopOutlined />} title="Integrations">
                <Menu.Item key="3">Git</Menu.Item>
                  <SubMenu key="sub2" title="Trello">
                      <Menu.Item key="5">Trello Link 1</Menu.Item>
                      <Menu.Item key="6">Trello Link 2</Menu.Item>
                  </SubMenu>
                <Menu.Item key="7">Google Drive</Menu.Item>
              </SubMenu>
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
        </Layout>
  )
}

export default SideBar
