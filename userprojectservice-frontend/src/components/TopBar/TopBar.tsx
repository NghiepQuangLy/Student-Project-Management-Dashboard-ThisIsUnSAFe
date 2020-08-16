import React, { FunctionComponent, useContext } from "react"
import { BarContainerContext } from "../BarContainer/BarContainer"
import BurgerButton from "../BurgerButton/BurgerButton"
import styles from "./TopBar.module.css"
import { GoogleLogout } from 'react-google-login';


interface TopBarProps {
  shouldContainSideBar: Boolean
  pageTitle: string
}

const responseGoogle = () => {
  window.location.href = "/"
}

const TopBar: FunctionComponent<TopBarProps> = ({ shouldContainSideBar, pageTitle }) => {
  const { setIsShowSidebar } = useContext(BarContainerContext)
  return (
    <div className={styles.LeftSideBar__TopSection}>
      {shouldContainSideBar && <BurgerButton onClick={() => setIsShowSidebar(true)} />}
      <div className={styles.pageTitle}>{pageTitle}</div>

      <GoogleLogout
        clientId="12178522373-e5nmdu6ogip7e70f2sn645j30n55fgke.apps.googleusercontent.com"
        buttonText="Logout"
        onLogoutSuccess={responseGoogle}

        render={renderProps => (
          <a onClick={renderProps.onClick} className={styles.logout}>Log Out</a>
        )}
      >
      </GoogleLogout>
    </div>
  )
}
export default TopBar
