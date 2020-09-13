import { Integration } from "./Integration"

const MockIntegration: Integration = {
  getProject: async () =>
    new Promise((resolve) => {
      setTimeout(() => {
        resolve({
          projectId: "2",
          projectName: "TestProject2",
          projectGitIntegration: [
            {
              integrationId: "git1",
              integrationName: "gitname1"
            },
            {
              integrationId: "git1234",
              integrationName: "gitname2"
            },
            {
              integrationId: "gittesre22",
              integrationName: "gitname3"
            }
          ],
          projectGoogleDriveIntegration: [
            {
              integrationId: "GoogleDrive1",
              integrationName: "GoogleDriveoname1"
            },
            {
              integrationId: "GoogleDrive2",
              integrationName: "GoogleDriveoname2"
            },
            {
              integrationId: "GoogleDrive4",
              integrationName: "GoogleDriveoname3"
            },
            {
              integrationId: "GoogleDrivetesre22",
              integrationName: "GoogleDriveoname4"
            }
          ],
          projectGoogleFolderIds: ["1", "tesre22"],
          projectTrelloIntegration: [
            {
              integrationId: "trell1",
              integrationName: "trelloname1"
            },
            {
              integrationId: "trell2",
              integrationName: "trelloname2"
            },
            {
              integrationId: "trell50",
              integrationName: "trelloname3"
            },
            {
              integrationId: "trell501",
              integrationName: "trelloname3"
            },
            {
              integrationId: "trellotesre22",
              integrationName: "trelloname4"
            }
          ],
          projectTimesheet: "Timesheet"
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
