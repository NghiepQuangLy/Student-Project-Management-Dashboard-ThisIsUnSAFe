import React from "react"
import App from "./App"
import { act, create } from "react-test-renderer"
import MockIntegration from "./integrations/MockIntegration"

describe("app", () => {
  const originalHref = window.location.href

  afterEach(() => {
    window.history.replaceState(null, "", originalHref)
  })

  it("should show Navigation and Cart by default", async () => {
    await act(async () => {
      const { root } = await create(<App integration={MockIntegration} />)

      expect(root.findByType(App))
    })
  })
})
