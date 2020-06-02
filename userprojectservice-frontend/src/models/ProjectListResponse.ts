export interface Project {
  projectName: string | null
  projectId: string | null
}

export interface ProjectListResponse {
  projects: Project[]
}
