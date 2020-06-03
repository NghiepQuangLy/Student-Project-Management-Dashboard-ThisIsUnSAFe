import { ProjectResponse } from "../models/ProjectResponse"
import { UserResponse } from "../models/UserResponse"

export interface Integration {
  getProject(emailAddress: String, projectId: String): Promise<ProjectResponse>
  getUser(emailAddress: String): Promise<UserResponse>
}

const Integration: Integration = {
  async getProject(emailAddress: String, projectId: String) {
    return fetch(`${process.env.REACT_APP_HOST}/get-project?email=${emailAddress}&projectId=${projectId}`, {
      method: "GET"
    }).then(async (response) => {
      const responseBody = await response.text()

      if (response.status < 200 || response.status > 299) {
        try {
          return Promise.reject(JSON.parse(responseBody))
        } catch {
          return Promise.reject(responseBody)
        }
      }
      return Promise.resolve(JSON.parse(responseBody))
    })
  },

  async getUser(emailAddress: String) {
    return fetch(`${process.env.REACT_APP_HOST}/get-user?email=${emailAddress}`, {
      method: "GET"
    }).then(async (response) => {
      const responseBody = await response.text()

      if (response.status < 200 || response.status > 299) {
        try {
          return Promise.reject(JSON.parse(responseBody))
        } catch {
          return Promise.reject(responseBody)
        }
      }
      return Promise.resolve(JSON.parse(responseBody))
    })
  }
}

export default Integration
