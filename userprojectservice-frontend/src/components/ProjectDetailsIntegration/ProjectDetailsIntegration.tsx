import React, { FunctionComponent } from "react"
import styles from "./ProjectDetailsIntegration.module.css"
import {
  GIT_ID_QUERY,
  GOOGLE_DRIVE_ID_QUERY,
  PROJECT_DETAIL_CONTACTS_PATH,
  PROJECT_DETAIL_EXPORT_DATA_PATH,
  PROJECT_DETAIL_GIT_PATH,
  PROJECT_DETAIL_GOOGLE_DRIVE_PATH,
  PROJECT_DETAIL_PROJECT_PROBLEMS_PATH,
  PROJECT_DETAIL_REMINDERS_PATH,
  PROJECT_DETAIL_TIME_TRACKING_PATH,
  PROJECT_DETAIL_TRELLO_PATH,
  PROJECT_ID_QUERY,
  TRELLO_ID_QUERY,
  useQuery
} from "../../util/useQuery"

interface ProjectDetailsIntegrationProps {}

const ProjectDetailsIntegration: FunctionComponent<ProjectDetailsIntegrationProps> = () => {
  let query = useQuery()
  let url = ""
  const projectId = query?.get(PROJECT_ID_QUERY)

  let integrationIdQuery

  switch (window.location.pathname) {
    case PROJECT_DETAIL_GIT_PATH:
      const gitId = query?.get(GIT_ID_QUERY)
      integrationIdQuery = gitId ? `&git-id=${gitId}` : ""
      url = `${process.env.REACT_APP_GIT_URL}?project-id=${projectId}${integrationIdQuery}`
      console.log(url)
      break
    case PROJECT_DETAIL_TRELLO_PATH:
      const trelloId = query?.get(TRELLO_ID_QUERY)
      integrationIdQuery = trelloId ? `&trello-id=${trelloId}` : ""
      url = `${process.env.REACT_APP_TRELLO_URL}?project-id=${projectId}${integrationIdQuery}`
      console.log(url)
      break
    case PROJECT_DETAIL_GOOGLE_DRIVE_PATH:
      const driveId = query?.get(GOOGLE_DRIVE_ID_QUERY)
      integrationIdQuery = driveId ? `&drive-id=${driveId}` : ""
      url = `${process.env.REACT_APP_GOOGLE_DRIVE_URL}?project-id=${projectId}${integrationIdQuery}`
      console.log(url)
      break
    case PROJECT_DETAIL_REMINDERS_PATH:
      url = `${process.env.REACT_APP_REMINDER_URL}?project-id=${projectId}`
      console.log(url)
      break
    case PROJECT_DETAIL_PROJECT_PROBLEMS_PATH:
      url = `${process.env.REACT_APP_PROJECT_PROBLEM_URL}?project-id=${projectId}`
      console.log(url)
      break
    case PROJECT_DETAIL_EXPORT_DATA_PATH:
      url = `${process.env.REACT_APP_EXPORT_DATA_URL}?project-id=${projectId}`
      console.log(url)
      break
    case PROJECT_DETAIL_TIME_TRACKING_PATH:
      url = `${process.env.REACT_APP_TIME_TRACKING_URL}?project-id=${projectId}`
      console.log(url)
      break
    case PROJECT_DETAIL_CONTACTS_PATH:
      url = `${process.env.REACT_APP_CONTACTS_URL}?project-id=${projectId}`
      console.log(url)
      break
    default:
      break
  }

  return (
    <div>
      <iframe className={styles.Iframe} src={url} scrolling={"no"} title={"integrationIframe"} />
    </div>
  )
}

export default ProjectDetailsIntegration
