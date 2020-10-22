import { Integration } from "./Integration"

const MockIntegration: Integration = {
  getProject: async (emailAddress: string, projectId: string) =>
    new Promise((resolve) => {
      setTimeout(() => {
        resolve({
          projectId: projectId,
          projectName: "TestProject2",
          moodleLink: "https://test.com.au",
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
          projectTimesheet: "Timesheet",
          projectIntegrationTable: [
            {
              emailAddress: "gtru2@student.monash.edu",
              gitIntegrationLastModified: "22 days ago",
              googleDriveIntegrationLastModified: "Unavailable",
              trelloIntegrationLastModified: "NA"
            },
            {
              emailAddress: "zwuu0008@student.monash.edu",
              gitIntegrationLastModified: "13 days ago",
              googleDriveIntegrationLastModified: "Unavailable",
              trelloIntegrationLastModified: "NA"
            },
            {
              emailAddress: "svis0004@student.monash.edu",
              gitIntegrationLastModified: "23 days ago",
              googleDriveIntegrationLastModified: "Unavailable",
              trelloIntegrationLastModified: "NA"
            },
            {
              emailAddress: "rhow0003@student.monash.edu",
              gitIntegrationLastModified: "29 days ago",
              googleDriveIntegrationLastModified: "Unavailable",
              trelloIntegrationLastModified: "NA"
            },
            {
              emailAddress: "qlyy0001@student.monash.edu",
              gitIntegrationLastModified: "18 days ago",
              googleDriveIntegrationLastModified: "Unavailable",
              trelloIntegrationLastModified: "NA"
            }
          ],
          projectReminderTable: [
            {
              reminderActivity: "test1",
              reminderUnitCode: "FIT3170",
              reminderUnitName: "Software Engineering Practices",
              reminderDate: "11/10/2020",
              reminderTime: "11:30PM"
            },
            {
              reminderActivity: "test2",
              reminderUnitCode: "FIT3170",
              reminderUnitName: "Software Engineering Practices",
              reminderDate: "25/10/2020",
              reminderTime: "3:30PM"
            },
            {
              reminderActivity: "test3",
              reminderUnitCode: "FIT3170",
              reminderUnitName: "Software Engineering Practices",
              reminderDate: "11/10/2020",
              reminderTime: "7:30AM"
            }
          ]
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
              projectUnitCode: "FIT2099",
              projectYear: "2019",
              projectSemester: "2"
            },
            {
              projectName: "projectName3",
              projectId: "projectId4",
              projectUnitCode: "FIT2099",
              projectYear: "2020",
              projectSemester: "1"
            },
            {
              projectName: "projectName5",
              projectId: "projectId6",
              projectUnitCode: "FIT3099",
              projectYear: "2020",
              projectSemester: "2"
            }
          ]
        })
      }, 1000)
    })
}

export default MockIntegration
