import React, { FunctionComponent, useReducer } from "react"
import { Integration } from "./integrations/Integration"
import { BrowserRouter as Router, Route, Switch } from "react-router-dom"
import Login from "./pages/Login/Login"
import AppReducer from "./state/AppReducer"
import AppInitialState from "./state/AppState"
import { setBasepath } from "hookrouter"
import ProjectList from "./pages/ProjectList/ProjectList"
import ProjectDetails from "./pages/ProjectDetail/ProjectDetails"
import GoogleAuthProvider from "./components/GoogleAuthProvider/GoogleAuthProvider"
import Example from "./pages/Example/Example"
import {
  PROJECT_DETAIL_CONTACTS_PATH,
  PROJECT_DETAIL_GIT_PATH,
  PROJECT_DETAIL_GOOGLE_DRIVE_PATH,
  PROJECT_DETAIL_PATH,
  PROJECT_DETAIL_REMINDERS_PATH,
  PROJECT_DETAIL_TIME_TRACKING_PATH,
  PROJECT_DETAIL_TRELLO_PATH
} from "./util/useQuery"
import SAFeAndAgileFeatures from "./pages/SAFeAndAgileFeatures/SAFeAndAgileFeatures"

setBasepath(process.env.REACT_APP_CONTEXT_PATH || "")

// Provides App Props integration
interface AppProps {
  integration: Integration
  children?: never
}

/** This method returns the App component which is the root of the entire application.
 * @param integration Allows API calls to be used in function
 * @return The HTML for the TabPanel
 */
const App: FunctionComponent<AppProps> = ({ integration }) => {
  const [state, dispatch] = useReducer(AppReducer, AppInitialState)
  return (
    <GoogleAuthProvider>
      <Router>
        <div>
          {/* A <Switch> looks through its children <Route>s and
            renders the first one that matches the current URL.
            REMEMBER to have longer path at the top
        */}
          <Switch>
            <Route path={`${PROJECT_DETAIL_GIT_PATH}`}>
              <ProjectDetails integration={integration} state={state} dispatch={dispatch} />
            </Route>
            <Route path={`${PROJECT_DETAIL_TRELLO_PATH}`}>
              <ProjectDetails integration={integration} state={state} dispatch={dispatch} />
            </Route>
            <Route path={`${PROJECT_DETAIL_GOOGLE_DRIVE_PATH}`}>
              <ProjectDetails integration={integration} state={state} dispatch={dispatch} />
            </Route>
            <Route path={`${PROJECT_DETAIL_REMINDERS_PATH}`}>
              <ProjectDetails integration={integration} state={state} dispatch={dispatch} />
            </Route>
            <Route path={`${PROJECT_DETAIL_TIME_TRACKING_PATH}`}>
              <ProjectDetails integration={integration} state={state} dispatch={dispatch} />
            </Route>
            <Route path={`${PROJECT_DETAIL_CONTACTS_PATH}`}>
              <ProjectDetails integration={integration} state={state} dispatch={dispatch} />
            </Route>
            <Route path="/SAFeAndAgileFeatures">
              <SAFeAndAgileFeatures integration={integration} state={state} dispatch={dispatch} />
            </Route>
            <Route path="/projects">
              <ProjectList integration={integration} state={state} dispatch={dispatch} />
            </Route>
            <Route path={`${PROJECT_DETAIL_PATH}`}>
              <ProjectDetails integration={integration} state={state} dispatch={dispatch} />
            </Route>
            <Route path="/example">
              <Example integration={integration} state={state} dispatch={dispatch} />
            </Route>
            <Route path="/">
              <Login integration={integration} state={state} dispatch={dispatch} />
            </Route>
          </Switch>
        </div>
      </Router>
    </GoogleAuthProvider>
  )
}

export default App
