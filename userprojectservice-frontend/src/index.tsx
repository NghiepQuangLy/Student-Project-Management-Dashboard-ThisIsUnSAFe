import React from "react"
import ReactDOM from "react-dom"
import "./index.css"
import App from "./App"
import { Integration } from "./integrations/Integration"

const init = async () => {
  const integrationModule = await import(`./integrations/${process.env.REACT_APP_INTEGRATION_TYPE}Integration.ts`)

  const integration: Integration = integrationModule.default

  ReactDOM.render(
    <React.StrictMode>
      <App integration={integration} />
    </React.StrictMode>,
    document.getElementById("root")
  )
}

init()

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
