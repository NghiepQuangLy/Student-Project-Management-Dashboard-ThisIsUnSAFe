import { useLocation } from "react-router-dom"

export const PROJECT_ID_QUERY = "project-id"
export const GIT_ID_QUERY = "git-id"
export const TRELLO_ID_QUERY = "trello-id"
export const GOOGLE_DRIVE_ID_QUERY = "googledrive-id"

export const PROJECT_DETAIL_PATH = "/project"
export const PROJECT_DETAIL_GIT_PATH = `${PROJECT_DETAIL_PATH}/git`
export const PROJECT_DETAIL_TRELLO_PATH = `${PROJECT_DETAIL_PATH}/trello`
export const PROJECT_DETAIL_GOOGLE_DRIVE_PATH = `${PROJECT_DETAIL_PATH}/google-drive`
export const PROJECT_DETAIL_REMINDERS_PATH = `${PROJECT_DETAIL_PATH}/reminders`
export const PROJECT_DETAIL_PROJECT_PROBLEMS_PATH = `${PROJECT_DETAIL_PATH}/project-problems`
export const PROJECT_DETAIL_EXPORT_DATA_PATH = `${PROJECT_DETAIL_PATH}/export-data`
export const PROJECT_DETAIL_TIME_TRACKING_PATH = `${PROJECT_DETAIL_PATH}/time-tracking`
export const PROJECT_DETAIL_CONTACTS_PATH = `${PROJECT_DETAIL_PATH}/contacts`

export const useQuery = () => new URLSearchParams(useLocation().search)
