import * as AppAction from "./AppAction"
import AppReducer from "./AppReducer"
import { ProjectListStatus } from "../models/ProjectListStatus"
import { AppState, Project } from "./ProjectListState"
import { InitialProjectList } from "../usecase/ProjectList"

describe("AppReducer", () => {
  describe("PROJECT_LIST_LOADING", () => {
    it("should set order summary loading state to true", () => {
      const state = createBasicAppState()

      const action = AppAction.projectListLoading()
      const result = AppReducer(state, action)

      expect(result.projectListStatus).toEqual(ProjectListStatus.LOADING)
    })

    it("should not modify any other state", () => {
      const state = createBasicAppState()

      const action = AppAction.projectListLoading()
      const result = AppReducer(state, action)

      expect(result.projects).toEqual([])
    })
  })

  describe("PROJECT_LIST_SUCCESS", () => {
    it("should set loading status to PROJECT_LIST_SUCCESS", () => {
      const state = createBasicAppState()
      state.projectListStatus = ProjectListStatus.LOADING
      const orderSummary = createProjectList([
        {
          projectName: "projectName1",
          projectId: "projectId1"
        },
        {
          projectName: "projectName2",
          projectId: "projectId2"
        }
      ])

      const action = AppAction.projectListSuccess(orderSummary)
      const result = AppReducer(state, action)

      expect(result.projectListStatus).toEqual(ProjectListStatus.SUCCESS)
    })

    it("should update cart content", () => {
      const state = createBasicAppState()
      const projectList = createProjectList([
        {
          projectName: "projectName1",
          projectId: "projectId1"
        }
      ])

      const action = AppAction.projectListSuccess(projectList)
      const result = AppReducer(state, action)

      expect(result.projects).toEqual(projectList.projects)
    })
  })

  const createBasicAppState = (): AppState => ({
    projects: [],
    projectListStatus: ProjectListStatus.INITIAL
  })

  const createProjectList = (projects: Project[]): InitialProjectList => ({
    projects: projects
  })
})
