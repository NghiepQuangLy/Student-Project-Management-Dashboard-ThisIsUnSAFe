import { AppStatus } from "../models/AppStatus"

export interface ProjectDetail {
  projectName: string | null
  projectId: string
  moodleLink: string | null
  projectTimesheet: string | null
  projectGitIntegrations: ProjectIntegration[]
  projectGoogleDriveIntegrations: ProjectIntegration[]
  projectGoogleFolderIds: string[]
  projectTrelloIntegrations: ProjectIntegration[]
  projectIntegrationTable: IntegrationTable[]
  projectReminderTable: ReminderTable[]
  projectBurndownChart: BurndownChart | null
}

export interface ProjectIntegration {
  integrationId: string
  integrationName: string
}

export interface IntegrationTable {
  emailAddress: string
  gitIntegrationLastModified: string
  googleDriveIntegrationLastModified: string
  trelloIntegrationLastModified: string
}

export interface ReminderTable {
  reminderName: string
  reminderProject: string
  reminderDueDate: string
  reminderDesc: string
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
  isUserEmailInvalid: boolean
  currentProject: ProjectDetail | null
  user: User | null
}

export interface BurndownChart {
  boardName: string
  listSizes: TrelloMap
}

export interface TrelloMap {
  [trelloKey: string]: CardMap
}

export interface CardMap {
  [cardKey: string]: TrelloCard
}

export interface TrelloCard {
  name: string
  size: number
}

const AppInitialState: AppState = {
  projectDetailStatus: AppStatus.INITIAL,
  userDetailStatus: AppStatus.INITIAL,
  isUserEmailInvalid: false,
  currentProject: null,
  user: null
}

export default AppInitialState
