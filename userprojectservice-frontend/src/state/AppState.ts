import { AppStatus } from "../models/AppStatus"

export interface ProjectDetail {
  projectName: string | null
  projectId: string | null
  projectGitIntegrations: ProjectIntegration[]
  projectGoogleDriveIntegrations: ProjectIntegration[]
  projectGoogleFolderIds: string[]
  projectTrelloIntegrations: ProjectIntegration[]
}

export interface ProjectIntegration {
  integrationId: string
  integrationName: string
}

export interface Project {
  projectName: string | null
  projectId: string
  projectUnitCode: string | null
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
