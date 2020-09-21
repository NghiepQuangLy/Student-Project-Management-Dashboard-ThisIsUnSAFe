import React, { useState, useLayoutEffect } from "react"
import { Page } from "../Page"
import { Redirect, useHistory } from "react-router-dom"
import * as AppAction from "../../state/AppAction"
import { AppStatus } from "../../models/AppStatus"
import clsx from "clsx"
import Typography from "@material-ui/core/Typography"
import Container from "@material-ui/core/Container"
import InputBase from "@material-ui/core/InputBase"
import { Grid } from "@material-ui/core"
import Paper from "@material-ui/core/Paper"
import Box from "@material-ui/core/Box"
import Copyright, { useStyles } from "../Resources/Styles"
import BarContainer from "../../components/BarContainer/BarContainer"
import { useGoogleAuth } from "../../components/GoogleAuthProvider/GoogleAuthProvider"
import { PROJECT_ID_QUERY } from "../../util/useQuery"
import Loading from "../../components/Loading/Loading"
import TreeView from "@material-ui/lab/TreeView"
import ExpandMoreIcon from "@material-ui/icons/ExpandMore"
import ChevronRightIcon from "@material-ui/icons/ChevronRight"
import TreeItem from "@material-ui/lab/TreeItem"
import styles from "./ProjectList.module.css"
import { Project } from "../../state/AppState"

export interface Dictionary<T> {
  [id: string]: T
}

export interface ProjectDictionary {
  id: Dictionary<ProjectDictionary>
  data?: ProjectData[] | undefined
}

export interface ProjectData {
  projectId: string
  projectName: string
}

const ProjectList: Page = ({ integration, state, dispatch }) => {
  const history = useHistory()
  const [projectList, setProjectList] = useState<Project[] | undefined>([])

  const isEmpty = state.userDetailStatus === AppStatus.SUCCESS && state.user?.projects.length === 0

  const { googleUser, isInitialized, isSignedIn } = useGoogleAuth()
  const emailAddress = googleUser?.getBasicProfile()?.getEmail()

  useLayoutEffect(() => {
    if (state.userDetailStatus === AppStatus.INITIAL && emailAddress) {
      dispatch(AppAction.userDetailLoading())
      integration.getUser(emailAddress).then((user) => {
        dispatch(AppAction.userDetailSuccess(user))
      })
    }

    setProjectList(state.user?.projects)
  }, [dispatch, integration, state.userDetailStatus, state.user, isInitialized, emailAddress])

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
          key={`projectId-${projectData.projectId}`}
          nodeId={`projectId-${projectData.projectId}`}
          label={projectData.projectName}
          onClick={() => handleOnShowProjectDetailsClicked(projectData.projectId)}
        />
      )
    })

  const renderCont = (root: Dictionary<ProjectDictionary>) =>
    Object.keys(root).map((node) => {
      return (
        <TreeItem key={node} nodeId={node} label={node}>
          {renderTree(root[node])}
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
    let units: ProjectDictionary = { id: {} }

    projects.forEach((singleProject) => {
      let year: ProjectDictionary = { id: {} }
      let semester: ProjectDictionary = { id: {} }
      let project: ProjectDictionary = { id: {} }

      let projectUnit = singleProject.projectUnitCode || "unit"
      let projectYear = singleProject.projectYear || "year"
      let projectSemester = singleProject.projectSemester || "semester"
      let projectId = singleProject.projectId || "n/a"
      let projectName = singleProject.projectName || "n/a"

      if (!units.id[projectUnit]) {
        units.id[projectUnit] = year
      }

      const yearData = units.id[projectUnit]
      if (!yearData.id[projectYear]) {
        yearData.id[projectYear] = semester
      }

      const semesterData = yearData.id[projectYear]
      if (!semesterData.id[projectSemester]) {
        semesterData.id[projectSemester] = project
      }

      const projectData = semesterData.id[projectSemester]
      if (!projectData.data) {
        projectData.data = []
      }
      projectData.data.push({
        projectId: projectId,
        projectName: projectName
      })
    })

    return renderTree(units)
  }

  const filterProjects = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
    setProjectList(state.user?.projects.filter((project) => (project.projectName ? project.projectName.includes(e.target.value) : null)))
  }

  return (
    <div>
      {!isInitialized ? (
        <Loading iconColor={"black"} />
      ) : isSignedIn ? (
        emailAddress ? (
          <BarContainer shouldContainSideBar={false}>
            <div className={classes.root}>
              {/*<CssBaseline />*/}
              {/*<AppBar position="absolute" color="primary" className={clsx(classes.appBar, !open && classes.appBarShift)}>*/}
              {/*  <Toolbar className={classes.toolbar}>*/}
              {/*    <Typography component="h1" variant="h6" color="inherit" noWrap className={classes.title}>*/}
              {/*      Project List*/}
              {/*    </Typography>*/}
              {/*  </Toolbar>*/}
              {/*</AppBar>*/}
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
                              <strong>Email Address:</strong> {emailAddress}{" "}
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
                              float: "right"
                            }}
                            placeholder="Search Projects"
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
                                defaultCollapseIcon={<ExpandMoreIcon />}
                                defaultExpanded={["root"]}
                                defaultExpandIcon={<ChevronRightIcon />}
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
          </BarContainer>
        ) : (
          <h1>something went wrong</h1>
        )
      ) : (
        <Redirect to="/" />
      )}
    </div>
  )
}

export default ProjectList
