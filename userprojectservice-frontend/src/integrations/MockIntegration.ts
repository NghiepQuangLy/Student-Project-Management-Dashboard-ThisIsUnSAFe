import { Integration } from "./Integration"

const MockIntegration: Integration = {
  getProject: async () =>
    new Promise((resolve) => {
      setTimeout(() => {
        resolve({
          projectId: "2",
          projectName: "TestProject2",
          projectGitIds: ["1", "1234", "tesre22"],
          projectGoogleDocIds: ["1", "2", "4", "tesre22"],
          projectTrelloIds: ["1", "2", "50", "501", "tesre22"]
        })
      }, 2000)
    }),

  getUser: async () =>
    new Promise((resolve) => {
      setTimeout(() => {
        resolve({
          givenName: "Mike",
          familyName: "Ly",
          emailAddress: "mike@gmail.com",
          userGroup: "tstuserG",
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
