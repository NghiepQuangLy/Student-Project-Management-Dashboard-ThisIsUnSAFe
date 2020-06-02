import React, { FunctionComponent, useReducer } from "react"
import styles from "./App.module.css"
import { Integration } from "./integrations/Integration"
import routeConfig from "./routeConfig"
import AppReducer from "./state/AppReducer"
import AppInitialState from "./state/ProjectListState"
import { navigate, setBasepath, useRoutes } from "hookrouter"

setBasepath(process.env.REACT_APP_CONTEXT_PATH || "")

interface AppProps {
  integration: Integration
  children?: never
}

const App: FunctionComponent<AppProps> = ({ integration }) => {
  const { page: CurrentRoute } = useRoutes(routeConfig)
  const [state, dispatch] = useReducer(AppReducer, AppInitialState)

  return (
    <main className={styles.Main}>
      {(CurrentRoute && <CurrentRoute integration={integration} state={state} dispatch={dispatch} />) || navigate("/", true)}
    </main>
  )
}

export default App
