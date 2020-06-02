import { Page } from "./pages/Page"
import ProjectList from "./pages/ProjectList/ProjectList"
import Login from "./pages/Login/Login"

interface RouteConfig {
  [k: string]: () => { page: Page }
}

const routeConfig: RouteConfig = {
  "/projects": () => {
    return { page: ProjectList }
  },
  "/": () => {
    return { page: Login }
  }
}

export default routeConfig
