import { AppStatus } from "../models/AppStatus"

export interface ProjectDetail {
  projectName: string | null
  projectId: string | null
  projectGitIds: string[]
  projectGoogleDriveIds: string[]
  projectGoogleFolderIds: string[]
  projectTrelloIds: string[]
}

export interface Project {
  projectName: string | null
  projectId: string
}

export interface User {
  givenName: string | null
  familyName: string | null
  emailAddress: string | null
  userGroup: string | null
  projects: Project[]
}

export interface AppState {
  projectListStatus: AppStatus
  projectStatus: AppStatus
  userStatus: AppStatus
  currentProject: ProjectDetail | null
  user: User | null
}

const AppInitialState: AppState = {
  projectListStatus: AppStatus.INITIAL,
  projectStatus: AppStatus.INITIAL,
  userStatus: AppStatus.INITIAL,
  currentProject: null,
  user: null
}

export default AppInitialState
