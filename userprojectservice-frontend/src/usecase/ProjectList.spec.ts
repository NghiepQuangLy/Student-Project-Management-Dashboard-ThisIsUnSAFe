import { Integration } from "../integrations/Integration"
import { ProjectListResponse } from "../models/ProjectListResponse"
import * as ProjectListUseCase from "./ProjectList"

describe("OrderSummaryUseCase", () => {
  describe("loadInitialProjectList", () => {
    it("should return orders", async () => {
      const projectListResponse = {
        projects: [
          {
            projectName: "projectName",
            projectId: "projectId1"
          },
          {
            projectName: "projectName",
            projectId: "projectId"
          }
        ]
      } as ProjectListResponse

      const integration = {
        getProjectList: async () => projectListResponse
      } as Integration

      const result = await ProjectListUseCase.loadInitialProjectList(integration)
      expect(result).toEqual({
        projects: [
          {
            projectName: "projectName",
            projectId: "projectId1"
          },
          {
            projectName: "projectName",
            projectId: "projectId"
          }
        ]
      })
    })
  })
})
