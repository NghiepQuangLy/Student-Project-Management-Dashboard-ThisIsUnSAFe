import { Integration } from "../integrations/Integration"
import { Project } from "../models/UserResponse"

export interface InitialProject {
  projectId: string | null
  projectName: string | null
  projectGitIds: string[]
  projectGoogleDocIds: string[]
  projectTrelloIds: string[]
}

export const loadInitialProject = async (integration: Integration, emailAddress: String, projectId: String): Promise<InitialProject> => {
  const projectResponse = await integration.getProject(emailAddress, projectId)

  return {
    projectId: projectResponse.projectId,
    projectName: projectResponse.projectName,
    projectGitIds: projectResponse.projectGitIds,
    projectGoogleDocIds: projectResponse.projectGoogleDocIds,
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
  const UserResponse = await integration.getUser(emailAddress)

  return {
    givenName: UserResponse.givenName,
    familyName: UserResponse.familyName,
    emailAddress: UserResponse.emailAddress,
    userGroup: UserResponse.userGroup,
    projects: UserResponse.projects
  }
}
