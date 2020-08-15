import React, { FunctionComponent, useState } from "react"

interface MenuBarProps {
  title?: string
}

export const MenuBarContext = React.createContext({})

const MenuBar: FunctionComponent<MenuBarProps> = ({ title, children }) => {
  const [isShowSidebar, setIsShowSidebar] = useState(false)

  return (
    <MenuBarContext.Provider value={{ isShowSidebar, setIsShowSidebar }}>
      <div className="LeftSideBar__container">
        <div
          className={`LeftSideBar__container__overlay LeftSideBar__container__overlay--${isShowSidebar ? "show" : "hide"}`}
          role="button"
          onClick={() => setIsShowSidebar(false)}
        ></div>
        {/*<TopSection />*/}
        {/*<LeftSection />*/}
      </div>
      <div>{title || "testTitle"}</div>
      {children}
    </MenuBarContext.Provider>
  )
}

export default MenuBar
