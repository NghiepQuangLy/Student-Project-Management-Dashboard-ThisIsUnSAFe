/* eslint-disable jsx-a11y/anchor-is-valid */
import React, { useContext } from 'react';
import BurgerButton from '../BurgerButton';
import { LeftSideBarContext } from '../index';
import './style.scss';

const LeftSection = () => {
  const { isShowSidebar, setIsShowSidebar } = useContext(LeftSideBarContext);
  return (
    <div className={`LeftSideBar__LeftSection LeftSideBar__LeftSection--${isShowSidebar ? 'show' : 'hide'}`}>
      <div className="LeftSideBar__LeftSection__topWrapper">
        <BurgerButton
          onClick={() => setIsShowSidebar(false)}
        />
      </div>
      <ul className="LeftSideBar__LeftSection__menuWrapper">
        <li>
          <a
            href="#"
          >
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

export default LeftSection;
