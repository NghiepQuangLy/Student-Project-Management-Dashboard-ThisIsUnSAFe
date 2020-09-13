import { Integration } from "../integrations/Integration"
import { Project } from "../models/UserResponse"

export interface InitialUser {
  givenName: string | null
  familyName: string | null
  emailAddress: string | null
  userGroup: string | null
  projects: Project[]
}

export const loadInitialUser = async (integration: Integration, emailAddress: string): Promise<InitialUser> => {
  const userResponse = await integration.getUser(emailAddress)

  return {
    givenName: userResponse.firstName,
    familyName: userResponse.lastName,
    emailAddress: userResponse.emailAddress,
    userGroup: userResponse.userGroup,
    projects: userResponse.projects
  }
}
