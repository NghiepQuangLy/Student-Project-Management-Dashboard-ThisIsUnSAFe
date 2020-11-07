export interface ProjectResponse {
  projectId: string
  projectName: string | null
  moodleLink: string | null
  projectGitIntegration: Integration[]
  projectGoogleDriveIntegration: Integration[]
  projectGoogleFolderIds: string[]
  projectTrelloIntegration: Integration[]
  projectTimesheet: string | null
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
  reminderName: string
  reminderProject: string
  reminderDueDate: string
  reminderDesc: string
}
