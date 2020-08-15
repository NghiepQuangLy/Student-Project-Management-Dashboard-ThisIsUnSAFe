import React, { useContext } from 'react';
import BurgerButton from '../BurgerButton/BurgerButton';
import { MenuBarContext } from '../MenuBar/MenuBar';
import styles from "./SideBar.module.css"


const SideBar = () => {
    const { isShowSidebar, setIsShowSidebar } = useContext(MenuBarContext);
    const sidebarStyle = isShowSidebar ? styles.LeftSideBar__LeftSection__show : styles.LeftSideBar__LeftSection__hide
    return (
        <div className={[styles.LeftSideBar__LeftSection, sidebarStyle].join(" ")}>
            <div className={styles.LeftSideBar__LeftSection__topWrapper}>
                <BurgerButton
                    onClick={() => setIsShowSidebar(false)}
                />
            </div>
            <ul className={styles.LeftSideBar__LeftSection__menuWrapper}>
                <li>
                    <a href="#">
                        Project Name
                    </a>
                </li>
                <li>
                    <a
                        href="#"
                    >
                        Reminders
            </a>
                </li>
                <li>
                    <a
                        href="#"
                    >
                        Project Problems
            </a>
                </li>
                <li>
                    <a
                        href="#"
                    >
                        Export Data
            </a>
                </li>
                <li>
                    <a
                        href="#"
                    >
                        Time Tracking
            </a>
                </li>
                <li>
                    <a
                        href="#"
                    >
                        Contacts
            </a>
                </li>
                <li>
                    <a
                        href="#"
                    >
                        Sign out
            </a>
                </li>

            </ul>
        </div>
    );
};

export default SideBar;
