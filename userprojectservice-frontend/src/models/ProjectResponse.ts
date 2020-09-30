export interface ProjectResponse {
  projectId: string | null
  projectName: string | null
  moodleLink: string | null
  projectGitIntegration: Integration[]
  projectGoogleDriveIntegration: Integration[]
  projectGoogleFolderIds: string[]
  projectTrelloIntegration: Integration[]
  projectTimesheet: string
}

export interface Integration {
  integrationId: string
  integrationName: string
}
