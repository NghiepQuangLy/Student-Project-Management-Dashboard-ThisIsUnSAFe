export interface ProjectResponse {
  projectId: string | null
  projectName: string | null
  moodleLink: string | null
  projectGitIntegration: Integration[]
  projectGoogleDriveIntegration: Integration[]
  projectGoogleFolderIds: string[]
  projectTrelloIntegration: Integration[]
  projectTimesheet: string
  projectIntegrationTable: IntegrationTable[]
  projectReminderTable: ReminderTable[]
}

export interface Integration {
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
  reminderActivity: string
  reminderUnitCode: string
  reminderUnitName: string
  reminderDate: string
  reminderTime: string
}
