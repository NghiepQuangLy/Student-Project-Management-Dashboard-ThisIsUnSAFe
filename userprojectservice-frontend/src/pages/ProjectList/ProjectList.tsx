import React, { useLayoutEffect } from "react"
import "./ProjectList.module.css"
import { Page } from "../Page"
import { Redirect, useHistory } from "react-router-dom"
import * as AppAction from "../../state/AppAction"
import { AppStatus } from "../../models/AppStatus"
import clsx from "clsx"
import Typography from "@material-ui/core/Typography"
import Container from "@material-ui/core/Container"
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

export interface Node {
  id: string
  name: string
  projectId?: string
  data?: { [id: string]: Node }
}

const ProjectList: Page = ({ integration, state, dispatch }) => {
  const history = useHistory()

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
  }, [dispatch, integration, state.userDetailStatus, state.user, isInitialized, emailAddress])

  const handleOnShowProjectDetailsClicked = (projectId: string) => {
    history.push(`/project?${PROJECT_ID_QUERY}=${projectId}`)
  }

  // Page Styling
  const classes = useStyles()
  const userdetailheight = clsx(classes.paper, classes.userdetailheight)

  const renderEnd = (nodes: Node) => (
    <TreeItem key={nodes.id} nodeId={nodes.id} label={nodes.name} onClick={() => handleOnShowProjectDetailsClicked(nodes.projectId ? nodes.projectId : nodes.id)}>

    </TreeItem>
  )

  const renderCont = (nodes: Node) => (
    <TreeItem key={nodes.id} nodeId={nodes.id} label={nodes.name}>
      {nodes.data ? Object.keys(nodes.data).map((node) => renderTree(nodes.data ? nodes.data[node] : nodes)) : null}
    </TreeItem>
  )

  const renderTree = (nodes: Node) => {
    console.log("renderTree: 0", nodes)
    if (nodes.data === undefined) {
      //console.log("nodes.data === undefined", nodes)
      return renderEnd(nodes)
    } else {
      return renderCont(nodes)
    }
  }

  const calculate = () => {
    let units: { [id: string]: Node } = {}
    let projectListLength = state.user?.projects.length || 0
    let projectList = state.user?.projects.map((project) => project) || []
    let count = 1
    console.log("ProjectList: ", projectList)
    for (let i = 0; i < projectListLength; i++) {
      let project: { [id: string]: Node } = {}
      let semester: { [id: string]: Node } = {}
      let year: { [id: string]: Node } = {}
      let projectUnit = projectList[i].projectUnitCode || "n/a"
      let projectYear = projectList[i].projectYear || "n/a"
      let projectSemester = projectList[i].projectSemester || "n/a"
      let projectId = projectList[i].projectId || "n/a"
      let projectName = projectList[i].projectName || "n/a"

      if (units[projectUnit]) {
        units[projectUnit] = { id: units[projectUnit].id, name: projectUnit, data: units[projectUnit].data }
      } else {
        units[projectUnit] = { id: (count += 1).toString(), name: projectUnit, data: year }
      }

      let yearData = (units[projectUnit] && units[projectUnit]?.data) || year

      if (yearData[projectYear]) {
        yearData[projectYear] = { id: yearData[projectYear].id, name: projectYear, data: yearData[projectYear].data }
      } else {
        yearData[projectYear] = { id: (count += 1).toString(), name: projectYear, data: semester }
      }

      let semesterData = yearData[projectYear].data || semester

      if (semesterData[projectSemester]) {
        semesterData[projectSemester] = {
          id: semesterData[projectSemester].id,
          name: projectSemester,
          data: semesterData[projectSemester].data
        }
      } else {
        semesterData[projectSemester] = { id: (count += 1).toString(), name: projectSemester, data: project }
      }

      let projectData = semesterData[projectSemester].data || project

      if (!projectData[projectId]) {
        projectData[projectId] = { id: (count += 1).toString(), name: projectName, projectId: projectId }
        //console.log("!projectData: ", units, projectData)
      }
    }
    //console.log("Units: ", units)
    let root: Node = { id: "1", name: "Projects", data: units }
    return renderTree(root)
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
                        <Typography variant="h6" align={"center"} gutterBottom>
                          Project List
                        </Typography>
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
                          <div>
                            {" "}
                            <strong>Projects: </strong>{" "}
                          </div>
                        </Typography>
                        <Container style={{ maxHeight: 200, padding: 0, overflow: "auto" }}>
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
