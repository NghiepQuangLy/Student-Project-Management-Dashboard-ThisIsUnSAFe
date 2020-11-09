import React, { FunctionComponent, useState } from "react"
import styles from "./ProjectListLanding.module.css"
import Container from "@material-ui/core/Container"
import { Grid } from "@material-ui/core"
import Paper from "@material-ui/core/Paper"
import Typography from "@material-ui/core/Typography"
import Box from "@material-ui/core/Box"
import clsx from "clsx"
import Copyright, { useStyles } from "../../pages/Resources/Styles"
import { AppState } from "../../state/AppState"
import InputBase from "@material-ui/core/InputBase"
import { AppStatus } from "../../models/AppStatus"
import TreeView from "@material-ui/lab/TreeView"
import FolderOpenIcon from "@material-ui/icons/FolderOpen"
import FolderIcon from "@material-ui/icons/Folder"
import TreeItem from "@material-ui/lab/TreeItem"
import { Dictionary, ProjectData, ProjectDictionary } from "../../pages/ProjectList/ProjectList"
import { PROJECT_ID_QUERY } from "../../util/useQuery"
import { useHistory } from "react-router-dom"

interface ProjectListLandingProps {
  state: AppState
}

/**
 * This method returns the ProjectListLanding component which displays all of the user projects (appropriately
 * categorized into semester, year, and unit.
 * This components allows user to:
 *    1. Search for projects
 *    2. Interact with projects similar to navigating File Explorer in Windows
 * @param state The application state containing the user information including their projects and their information
 * @return The HTML for the ProjectListLanding component
 */
const ProjectListLanding: FunctionComponent<ProjectListLandingProps> = ({ state }) => {
  const history = useHistory()
  const [expandedItems, setExpandedItems] = useState([])
  const onNodeToggle = (event: any, nodeId: any) => {
    setExpandedItems(nodeId)
  }

  const [filter, setFilter] = useState<string>("")

  let projectList = state.user?.projects
  projectList = state.user?.projects.filter((project) => project.projectName?.includes(filter))

  const isEmpty = state.userDetailStatus === AppStatus.SUCCESS && state.user?.projects.length === 0

  const handleOnShowProjectDetailsClicked = (projectId: string) => {
    history.push(`/project?${PROJECT_ID_QUERY}=${projectId}`)
  }

  // Page Styling
  const classes = useStyles()
  const userdetailheight = clsx(classes.paper, classes.userdetailheight)

  const renderEnd = (projectDatas: ProjectData[]) =>
    projectDatas.map((projectData) => {
      return (
        <TreeItem
          key={`${projectData.projectKey}`}
          nodeId={`${projectData.projectKey}`}
          label={projectData.projectName}
          onClick={() => handleOnShowProjectDetailsClicked(projectData.projectId)}
        />
      )
    })

  const renderCont = (root: Dictionary<ProjectDictionary>) =>
    Object.entries(root).map((node) => {
      return (
        <TreeItem key={node[1].key} nodeId={node[1].key} label={node[0]}>
          {renderTree(root[node[0]])}
        </TreeItem>
      )
    })

  const renderTree = (root: ProjectDictionary) => {
    if (!root.data) {
      return renderCont(root.id)
    } else {
      return renderEnd(root.data)
    }
  }

  const calculate = () => {
    //const projects = state.user?.projects || []
    const projects = projectList || []
    let units: ProjectDictionary = { id: {}, key: "0" }
    let key = 1

    projects.forEach((singleProject) => {
      let year: ProjectDictionary = { id: {}, key: "0" }
      let semester: ProjectDictionary = { id: {}, key: "0" }
      let project: ProjectDictionary = { id: {}, key: "0" }

      let projectUnit = singleProject.projectUnitCode || "unit"
      let projectYear = singleProject.projectYear || "year"
      let projectSemester = singleProject.projectSemester || "semester"
      let projectId = singleProject.projectId || "n/a"
      let projectName = singleProject.projectName || "n/a"

      if (!units.id[projectUnit]) {
        units.id[projectUnit] = year
        units.id[projectUnit].key = key.toString()
        key += 1
      }

      const yearData = units.id[projectUnit]
      if (!yearData.id[projectYear]) {
        yearData.id[projectYear] = semester
        yearData.id[projectYear].key = key.toString()
        key += 1
      }

      const semesterData = yearData.id[projectYear]
      if (!semesterData.id[projectSemester]) {
        semesterData.id[projectSemester] = project
        semesterData.id[projectSemester].key = key.toString()
        key += 1
      }

      const projectData = semesterData.id[projectSemester]
      if (!projectData.data) {
        projectData.data = []
      }
      projectData.data.push({
        projectId: projectId,
        projectKey: key.toString(),
        projectName: projectName
      })
      key += 1
    })

    return renderTree(units)
  }

  const filterProjects = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
    setFilter(e.target.value)
  }

  return (
    <div className={classes.root}>
      <main className={classes.content}>
        <div className={classes.appBarSpacer} />
        <Container maxWidth="lg" className={classes.container}>
          <Grid container spacing={3}>
            {/* Project Details */}
            <Grid item xs={12} md={12} lg={12}>
              <Paper className={userdetailheight}>
                <div className={styles.HeaderContainer}>
                  <h1>Student Project Management Dashboard</h1>
                </div>
                <div className={styles.UserDetailsGroup}>
                  <h2>User Details:</h2>
                </div>
                <div className={styles.UserDetails}>
                  <Typography variant="body1" gutterBottom>
                    <div>
                      {" "}
                      <strong>Given Name:</strong> {state.user?.givenName}{" "}
                    </div>
                    <div>
                      {" "}
                      <strong>Family Name:</strong> {state.user?.familyName}{" "}
                    </div>
                    <div>
                      {" "}
                      <strong>Email Address:</strong> {state.user?.emailAddress}{" "}
                    </div>
                    <div>
                      {" "}
                      <strong>User Group:</strong> {state.user?.userGroup}{" "}
                    </div>
                  </Typography>
                </div>

                <br />
                <div className={styles.UserProjectsGroup}>
                  <h2>User Projects:</h2>

                  <InputBase
                    style={{
                      float: "left",
                      width: "50vh",
                      minWidth: "150px"
                    }}
                    placeholder="🔍 Search Projects"
                    inputProps={{ "aria-label": "search" }}
                    onChange={(e) => filterProjects(e)}
                  />

                  <Container style={{ maxHeight: "100vh", width: "100vh", padding: 0, overflow: "auto" }}>
                    {state.userDetailStatus === AppStatus.LOADING ? (
                      <h1>Loading</h1>
                    ) : isEmpty ? (
                      <h1>Empty History</h1>
                    ) : (
                      <TreeView
                        className={classes.root}
                        defaultCollapseIcon={<FolderOpenIcon />}
                        defaultExpanded={["root"]}
                        defaultExpandIcon={<FolderIcon />}
                        expanded={expandedItems}
                        onNodeToggle={onNodeToggle}
                        style={{ display: "block" }}
                      >
                        {calculate()}
                      </TreeView>
                    )}
                  </Container>
                </div>
              </Paper>
            </Grid>
          </Grid>
          <Box pt={4}>
            <Copyright />
          </Box>
        </Container>
      </main>
    </div>
  )
}

export default ProjectListLanding
