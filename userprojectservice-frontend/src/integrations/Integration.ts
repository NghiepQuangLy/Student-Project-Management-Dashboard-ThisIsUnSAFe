import { ProjectListResponse } from "../models/ProjectListResponse"

export interface Integration {
  getProjectList(): Promise<ProjectListResponse>
}

const Integration: Integration = {
  async getProjectList() {
    return fetch(`${process.env.REACT_TEST_HOST}/getprojects`, {
      method: "GET",
      credentials: "include"
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
