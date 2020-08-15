import React, { FunctionComponent } from "react"

interface BurgerButtonProps {
    onClick: any
}

const BurgerButton: FunctionComponent<BurgerButtonProps> = ({ onClick }) => {

    return (
        <div
            className="LeftSideBar__BurgerButton"
            role="button"
            onClick={onClick}
        >
            <i></i>
            <i></i>
            <i></i>
        </div>
    )
}
export default BurgerButton
