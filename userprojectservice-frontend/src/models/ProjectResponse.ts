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
