import { Integration } from "../integrations/Integration"
import { Project } from "../models/UserResponse"

export interface InitialProject {
  projectId: string | null
  projectName: string | null
  projectGitIds: string[]
  projectGoogleDriveIds: string[]
  projectGoogleFolderIds: string[]
  projectTrelloIds: string[]
}

export const loadInitialProject = async (integration: Integration, emailAddress: String, projectId: String): Promise<InitialProject> => {
  const projectResponse = await integration.getProject(emailAddress, projectId)

  return {
    projectId: projectResponse.projectId,
    projectName: projectResponse.projectName,
    projectGitIds: projectResponse.projectGitIds,
    projectGoogleDriveIds: projectResponse.projectGoogleDriveIds,
    projectGoogleFolderIds: projectResponse.projectGoogleFolderIds,
    projectTrelloIds: projectResponse.projectTrelloIds
  }
}

export interface InitialUser {
  givenName: string | null
  familyName: string | null
  emailAddress: string | null
  userGroup: string | null
  projects: Project[]
}

export const loadInitialUser = async (integration: Integration, emailAddress: String): Promise<InitialUser> => {
  const userResponse = await integration.getUser(emailAddress)

  return {
    givenName: userResponse.firstName,
    familyName: userResponse.lastName,
    emailAddress: userResponse.emailAddress,
    userGroup: userResponse.userGroup,
    projects: userResponse.projects
  }
}
