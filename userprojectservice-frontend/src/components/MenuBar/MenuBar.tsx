import React, { Dispatch, FunctionComponent, SetStateAction, useState } from "react"
import TopBar from '../TopBar/TopBar'

interface MenuBarProps {
  title?: string
}

interface ContextInterface {
  isShowSidebar: Boolean
  setIsShowSidebar: Dispatch<SetStateAction<boolean>>
};

export const MenuBarContext = React.createContext({} as ContextInterface);

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
        {<TopBar />}
        {/* {<LeftSection />} */}
      </div>
      <div>{title || "testTitle"}</div>
      {children}
    </MenuBarContext.Provider>
  )
}

export default MenuBar
