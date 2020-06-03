import { AppStatus } from "../models/AppStatus"

export interface ProjectDetail {
  projectName: string | null
  projectId: string | null
  projectGitIds: string[]
  projectGoogleDocIds: string[]
  projectTrelloIds: string[]
}

export interface Project {
  projectName: string | null
  projectId: string | null
}

export interface AppState {
  projectListStatus: AppStatus
  projectStatus: AppStatus
  projects: Project[]
  currentProject: ProjectDetail | null
}

const AppInitialState: AppState = {
  projectListStatus: AppStatus.INITIAL,
  projectStatus: AppStatus.INITIAL,
  projects: [],
  currentProject: null
}

export default AppInitialState
