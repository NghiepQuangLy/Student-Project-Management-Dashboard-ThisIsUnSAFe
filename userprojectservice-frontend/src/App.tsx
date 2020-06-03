import React, { FunctionComponent, useReducer } from "react"
import { Integration } from "./integrations/Integration"
import { BrowserRouter as Router, Link, Route, Switch } from "react-router-dom"
import Login from "./pages/Login/Login"
import AppReducer from "./state/AppReducer"
import AppInitialState from "./state/AppState"
import { setBasepath } from "hookrouter"
import ProjectList from "./pages/ProjectList/ProjectList"

setBasepath(process.env.REACT_APP_CONTEXT_PATH || "")

interface AppProps {
  integration: Integration
  children?: never
}

const App: FunctionComponent<AppProps> = ({ integration }) => {
  const [state, dispatch] = useReducer(AppReducer, AppInitialState)
  return (
    <Router>
      <div>
        <nav>
          <ul>
            <li>
              <Link to="/">Login</Link>
            </li>
            <li>
              <Link to="/projects">projects</Link>
            </li>
          </ul>
        </nav>

        {/* A <Switch> looks through its children <Route>s and
            renders the first one that matches the current URL.
            REMEMBER to have longer path at the top
        */}
        <Switch>
          <Route path="/projects">
            <ProjectList integration={integration} state={state} dispatch={dispatch} />
          </Route>
          <Route path="/">
            <Login integration={integration} state={state} dispatch={dispatch} />
          </Route>
        </Switch>
      </div>
    </Router>
  )
}

export default App
