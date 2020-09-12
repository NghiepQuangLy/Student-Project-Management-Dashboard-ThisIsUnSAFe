import { Integration } from "./Integration"

const MockIntegration: Integration = {
  getProject: async () =>
    new Promise((resolve) => {
      setTimeout(() => {
        resolve({
          projectId: "2",
          projectName: "TestProject2",
          projectGitIds: ["1", "1234", "tesre22"],
          projectGoogleDriveIds: ["1", "2", "4", "tesre22"],
          projectGoogleFolderIds: ["1", "tesre22"],
          projectTrelloIds: ["1", "2", "50", "501", "tesre22"]
        })
      }, 1000)
    }),

  // getProject: async () =>
  //   new Promise((resolve, reject) => {
  //     setTimeout(() => {
  //       reject({})
  //     }, 1000)
  //   }),

  getUser: async () =>
    new Promise((resolve) => {
      setTimeout(() => {
        resolve({
          firstName: "Mike",
          lastName: "Ly",
          emailAddress: "mike@gmail.com",
          userGroup: "tstuserG",
          projects: [
            {
              projectName: "projectName1",
              projectId: "projectId2",
              projectUnit: "FIT2099",
              projectYear: "2019",
              projectSemester: "2"
            },
            {
              projectName: "projectName3",
              projectId: "projectId4",
              projectUnit: "FIT2099",
              projectYear: "2020",
              projectSemester: "1"
            },
            {
              projectName: "projectName5",
              projectId: "projectId6",
              projectUnit: "FIT3099",
              projectYear: "2020",
              projectSemester: "2"
            }
          ]
        })
      }, 1000)
    })
}

export default MockIntegration
