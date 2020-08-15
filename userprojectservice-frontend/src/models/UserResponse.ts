export interface Project {
  projectName: string | null
  projectId: string
}

export interface UserResponse {
  firstName: string | null
  lastName: string | null
  emailAddress: string | null
  userGroup: string | null
  projects: Project[]
}
