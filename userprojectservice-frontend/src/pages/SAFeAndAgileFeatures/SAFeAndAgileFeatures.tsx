import React from "react"
import { Page } from "../Page"
import BarContainer from "../../components/BarContainer/BarContainer";
import styles from "./SAFeAndAgileFeatures.module.css"

const SAFeAndAgileFeatures: Page = ({ integration, state, dispatch }) => {
  return (
      <BarContainer shouldContainSideBar={false}>
        <iframe className={styles.Iframe} src={`${process.env.REACT_APP_SAFE_AND_AGILE_FEATURES_URL}`} scrolling={"no"} title={"integrationIframe"} />
      </BarContainer>
  )
}

export default SAFeAndAgileFeatures
