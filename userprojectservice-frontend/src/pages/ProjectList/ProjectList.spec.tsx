import React from "react"
import { create } from "react-test-renderer"
import ProjectList from "./ProjectList"
import AppInitialState, { AppState } from "../../state/ProjectListState"
import { Integration } from "../../integrations/Integration"
import { ProjectListStatus } from "../../models/ProjectListStatus"

const createMockState = (): AppState => ({
  ...AppInitialState
})

const createMockIntegration = (): Integration => ({
  getProjectList: () => new Promise(() => {})
})

describe("Test", () => {
  it("should render ProjectList when `projectListStatus` is `LOADING`", () => {
    const state = createMockState()
    state.projectListStatus = ProjectListStatus.LOADING

    const { root } = create(<ProjectList integration={createMockIntegration()} state={state} dispatch={() => {}} />)

    expect(root.findByType(ProjectList)) // temporary use ProjectList , will fix after we have loading component
  })
})
