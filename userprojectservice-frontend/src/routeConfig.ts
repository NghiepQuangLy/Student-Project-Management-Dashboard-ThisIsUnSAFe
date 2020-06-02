import { Page } from "./pages/Page"
import ProjectList from "./pages/ProjectList/ProjectList"

interface RouteConfig {
  [k: string]: () => { page: Page }
}

const routeConfig: RouteConfig = {
  "/": () => {
    return { page: ProjectList }
  }
}

export default routeConfig
