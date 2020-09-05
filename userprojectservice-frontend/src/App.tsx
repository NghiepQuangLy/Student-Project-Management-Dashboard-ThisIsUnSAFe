import React, { FunctionComponent, useReducer } from "react"
import { Integration } from "./integrations/Integration"
import { BrowserRouter as Router, Route, Switch } from "react-router-dom"
import Login from "./pages/Login/Login"
import AppReducer from "./state/AppReducer"
import AppInitialState from "./state/AppState"
import { setBasepath } from "hookrouter"
import ProjectList from "./pages/ProjectList/ProjectList"
import ProjectDetails from "./pages/ProjectDetail/ProjectDetails"
import GitPage from "./pages/IntegrationPages/GitPage"
import TrelloPage from "./pages/IntegrationPages/TrelloPage"
import GoogleDrivePage from "./pages/IntegrationPages/GoogleDrivePage"
import GoogleFolderPage from "./pages/IntegrationPages/GoogleFolderPage"
import IntegrationPage from "./pages/IntegrationPages/IntegrationPage"
import AllEventsPage from "./pages/IntegrationPages/AllEventsPage"
import ExportDataPage from "./pages/IntegrationPages/ExportDataPage"
import TimeTrackingPage from "./pages/IntegrationPages/TimeTrackingPage"
import ContactsPage from "./pages/IntegrationPages/ContactsPage"
import GoogleAuthProvider from "./components/GoogleAuthProvider/GoogleAuthProvider"
import Example from "./pages/Example/Example"

setBasepath(process.env.REACT_APP_CONTEXT_PATH || "")

interface AppProps {
  integration: Integration
  children?: never
}

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
            <Route path="/gitPage">
              <GitPage />
            </Route>
            <Route path="/TrelloPage">
              <TrelloPage />
            </Route>
            <Route path="/GoogleDrivePage">
              <GoogleDrivePage />
            </Route>
            <Route path="/GoogleFolderPage">
              <GoogleFolderPage />
            </Route>
            <Route path="/projects">
              <ProjectList integration={integration} state={state} dispatch={dispatch} />
            </Route>
            <Route path="/project">
              <ProjectDetails integration={integration} state={state} dispatch={dispatch} />
            </Route>
            <Route path="/all-events">
              <AllEventsPage integration={integration} state={state} dispatch={dispatch} />
            </Route>
            <Route path="/export-data">
              <ExportDataPage integration={integration} state={state} dispatch={dispatch} />
            </Route>
            <Route path="/time-tracking">
              <TimeTrackingPage integration={integration} state={state} dispatch={dispatch} />
            </Route>
            <Route path="/contacts">
              <ContactsPage integration={integration} state={state} dispatch={dispatch} />
            </Route>
            <Route path="/integration">
              <IntegrationPage integration={integration} state={state} dispatch={dispatch} />
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
