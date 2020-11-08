import { Integration } from "./Integration"

/*
Mock API response
 */
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
              reminderName: "test1",
              reminderProject: "FIT3170",
              reminderDueDate: "2020-09-08T00:00:00.000+00:00",
              reminderDesc: "This is a sample description for Software Engineering Practices"
            },
            {
              reminderName: "test2",
              reminderProject: "FIT3170",
              reminderDueDate: "2020-09-09T00:00:00.000+00:00",
              reminderDesc: "This is a sample description for Software Engineering Practices"
            },
            {
              reminderName: "test3",
              reminderProject: "FIT3170",
              reminderDueDate: "2020-09-10T00:00:00.000+00:00",
              reminderDesc: "This is a sample description for Software Engineering Practices"
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
    }),

  getBurndownChart: async () =>
      new Promise((resolve) => {
        setTimeout(() => {
          resolve({
            boardName: "TestBoard",
            listSizes: {
              "2020-10-11T00:00:00Z": {

                "5ee7d560904e87427e0a1465": {
                  "name": "Stuff To Try (this is a list)",
                  "size": 1
                },
                "5f5c5762e82773415a6b7043": {
                  "name": "foo",
                  "size": 3
                }
              },
              "2020-10-11T06:59:30.713Z": {
                "5ee7d560904e87427e0a1465": {
                  "name": "Stuff To Try (this is a list)",
                  "size": 1
                },
                "5f5c5762e82773415a6b7043": {
                  "name": "foo",
                  "size": 1
                },
                "34": {
                  "name": "New List",
                  "size": 1
                }
              },
              "2021-10-11T00:00:00Z": {
                "5f5c5762e82773415a6b7043": {
                  "name": "foo",
                  "size": 3
                },
                "34": {
                  "name": "New List",
                  "size": 5
                }
              },
              "2022-10-11T00:00:00Z": {
                "5f5c5762e82773415a6b7043": {
                  "name": "foo",
                  "size": 3
                },
                "34": {
                  "name": "New",
                  "size": 5
                }
              }
            }
          })
        }, 1000)
      }),

  createUser: async () =>
    new Promise((resolve) => {
      setTimeout(() => {
        resolve({
          firstName: "Mike",
          lastName: "Ly",
          emailAddress: "mike@gmail.com",
          userGroup: "tstuserG",
          projects: []
        })
      }, 1000)
    }),

  updateUser: async () =>
    new Promise((resolve) => {
      setTimeout(() => {
        resolve()
      }, 1000)
    })
}

export default MockIntegration
