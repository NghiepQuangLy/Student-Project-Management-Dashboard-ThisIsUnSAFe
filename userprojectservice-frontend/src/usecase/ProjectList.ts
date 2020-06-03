import { Integration } from "../integrations/Integration"
import { Project } from "../models/ProjectListResponse"

export interface InitialProjectList {
  projects: Project[]
}

export const loadInitialProjectList = async (integration: Integration): Promise<InitialProjectList> => {
  const projectListResponse = await integration.getProjectList()

  return {
    projects: projectListResponse.projects
  }
}

export interface InitialProject {
  projectId: string | null
  projectName: string | null
  projectGitIds: string[]
  projectGoogleDocIds: string[]
  projectTrelloIds: string[]
}

export const loadInitialProject = async (integration: Integration): Promise<InitialProject> => {
  const projectResponse = await integration.getProject()

  return {
    projectId: projectResponse.projectId,
    projectName: projectResponse.projectName,
    projectGitIds: projectResponse.projectGitIds,
    projectGoogleDocIds: projectResponse.projectGoogleDocIds,
    projectTrelloIds: projectResponse.projectTrelloIds
  }
}
