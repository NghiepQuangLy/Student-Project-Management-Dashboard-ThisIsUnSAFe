export interface Project {
  projectName: string | null
  projectId: string | null
}

export interface UserResponse {
  givenName: string | null
  familyName: string | null
  emailAddress: string | null
  userGroup: string | null
  projects: Project[]
}
