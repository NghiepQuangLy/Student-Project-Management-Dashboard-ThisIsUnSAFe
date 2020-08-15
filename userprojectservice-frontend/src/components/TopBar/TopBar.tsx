import React, { useContext } from "react"
import { MenuBarContext } from '../MenuBar/MenuBar';
import BurgerButton from "../BurgerButton/BurgerButton"

const TopBar = () => {
    const { setIsShowSidebar } = useContext(MenuBarContext);
    return (
        <div className="LeftSideBar__TopSection">
            <BurgerButton
                onClick={() => setIsShowSidebar(true)}
            />
        </div>
    );
}
export default TopBar
