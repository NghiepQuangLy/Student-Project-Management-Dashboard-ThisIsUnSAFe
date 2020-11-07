import { ProjectResponse } from "../models/ProjectResponse"
import { UserResponse } from "../models/UserResponse"
import {BurndownChartResponse} from "../models/BurndownChartResponse";

export interface Integration {
  getProject(emailAddress: string, projectId: string): Promise<ProjectResponse>
  getUser(emailAddress: string): Promise<UserResponse>
  getBurndownChart(integrationId: string | undefined, token: string): Promise<BurndownChartResponse>
  createUser(emailAddress: string, givenName: string, familyName: string): Promise<UserResponse>
  updateUser(emailAddress: string, givenName: string, familyName: string): Promise<void>
}

/*
functions of calling APIs
 */
const Integration: Integration = {
  async getProject(emailAddress: string, projectId: string) {
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

  async getUser(emailAddress: string) {
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
  },

  async createUser(emailAddress: string, givenName: string, familyName: string) {
    return fetch(`${process.env.REACT_APP_HOST}/create-user`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({ emailAddress: emailAddress, givenName: givenName, familyName: familyName })
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

  async updateUser(emailAddress: string, givenName: string, familyName: string) {
    return fetch(`${process.env.REACT_APP_HOST}/update-user`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({ emailAddress: emailAddress, givenName: givenName, familyName: familyName })
    }).then(async (response) => {
      const responseBody = await response.text()

      if (response.status < 200 || response.status > 299) {
        try {
          return Promise.reject(JSON.parse(responseBody))
        } catch {
          return Promise.reject(responseBody)
        }
      }
      return Promise.resolve()
    })
  },

  async getBurndownChart(integrationId: string, token: string) {
    return fetch(`${process.env.REACT_APP_TRELLO_URL}/trello-service/data/burndown/${integrationId}?token=${token}`, {
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
