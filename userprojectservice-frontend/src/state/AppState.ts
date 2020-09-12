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
  projectUnit: string | null
  projectYear: string | null
  projectSemester: string | null
}

export interface User {
  givenName: string | null
  familyName: string | null
  emailAddress: string | null
  userGroup: string | null
  projects: Project[]
}

export interface AppState {
  projectDetailStatus: AppStatus
  userDetailStatus: AppStatus
  currentProject: ProjectDetail | null
  user: User | null
}

const AppInitialState: AppState = {
  projectDetailStatus: AppStatus.INITIAL,
  userDetailStatus: AppStatus.INITIAL,
  currentProject: null,
  user: null
}

export default AppInitialState
