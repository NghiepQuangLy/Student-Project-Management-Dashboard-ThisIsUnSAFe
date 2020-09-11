import React, { FunctionComponent } from "react"
import styles from "./ProjectDetailsIntegration.module.css"

interface ProjectDetailsIntegrationProps {}

const ProjectDetailsIntegration: FunctionComponent<ProjectDetailsIntegrationProps> = () => {
  const currentPath = window.location.pathname
  console.log(currentPath)

  // will change to different path when doing actual integration with the other team
  return (
    <div>
      <iframe className={styles.Iframe} src="http://localhost:3000/Example" scrolling={"no"} title={"integrationIframe"} />
    </div>
  )
}

export default ProjectDetailsIntegration
