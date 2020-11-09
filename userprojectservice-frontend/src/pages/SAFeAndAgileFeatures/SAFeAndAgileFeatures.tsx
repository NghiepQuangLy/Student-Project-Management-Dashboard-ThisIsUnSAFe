import React from "react"
import { Page } from "../Page"
import BarContainer from "../../components/BarContainer/BarContainer";
import styles from "./SAFeAndAgileFeatures.module.css"

const SAFeAndAgileFeatures: Page = ({ integration, state, dispatch }) => {
  return (
      <BarContainer shouldContainSideBar={false}>
        <iframe className={styles.Iframe} src={"http://spmd-git-frontend.s3-website-ap-southeast-2.amazonaws.com/useful-websites-frontend?fbclid=IwAR2So_5ZVBg8UsehScfVyRORqjPgvkwlNwDiOyYgfxXBTp-oVGNodnrctqA"} scrolling={"no"} title={"integrationIframe"} />
      </BarContainer>
  )
}

export default SAFeAndAgileFeatures
