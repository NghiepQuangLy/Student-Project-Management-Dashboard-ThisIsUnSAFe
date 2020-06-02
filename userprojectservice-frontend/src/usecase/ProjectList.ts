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
