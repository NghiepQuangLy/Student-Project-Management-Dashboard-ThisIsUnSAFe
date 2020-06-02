import { ProjectListStatus } from "../models/ProjectListStatus"

export interface Project {
  projectName: string | null
  projectId: string | null
}

export interface AppState {
  projectListStatus: ProjectListStatus
  projects: Project[]
}

const AppInitialState: AppState = {
  projectListStatus: ProjectListStatus.INITIAL,
  projects: []
}

export default AppInitialState
