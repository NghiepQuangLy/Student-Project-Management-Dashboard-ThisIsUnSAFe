import React, { FunctionComponent } from "react"
import styles from "./ProjectDetailsIntegration.module.css"

interface ProjectDetailsIntegrationProps {
  path: string
}

const ProjectDetailsIntegration: FunctionComponent<ProjectDetailsIntegrationProps> = ({ path }) => {
  console.log(path)
  return (
    <div>
      <iframe className={styles.Iframe} src="http://localhost:3000/Example" scrolling={"no"} />
    </div>
  )
}

export default ProjectDetailsIntegration
