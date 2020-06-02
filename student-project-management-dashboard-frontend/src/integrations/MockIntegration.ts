import { Integration } from "./Integration"

const MockIntegration: Integration = {
  getProjectList: async () =>
    new Promise((resolve) => {
      setTimeout(() => {
        resolve({
          projects: [
            {
              projectName: "projectName1",
              projectId: "projectId2"
            },
            {
              projectName: "projectName3",
              projectId: "projectId4"
            },
            {
              projectName: "projectName5",
              projectId: "projectId6"
            }
          ]
        })
      }, 2000)
    })
}

export default MockIntegration
