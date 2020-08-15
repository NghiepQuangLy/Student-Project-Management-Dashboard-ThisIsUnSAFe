import React, { useLayoutEffect } from "react"
import "./ProjectList.module.css"
import { Page } from "../Page"
import * as UseCase from "../../usecase/UseCase"
import * as AppAction from "../../state/AppAction"
import { AppStatus } from "../../models/AppStatus"

import AppBar from "@material-ui/core/AppBar"
import clsx from "clsx"

import Typography from "@material-ui/core/Typography"
import Container from "@material-ui/core/Container"
import Paper from "@material-ui/core/Paper"
import Box from "@material-ui/core/Box"
import Copyright, { useStyles } from "../Resources/Styles"
import Button from "@material-ui/core/Button"
import { Divider, Grid, List, ListItem, Tab, Tabs } from "@material-ui/core"
import TabPanel, { a11yProps } from "../Resources/Tabs"
import { Redirect } from "react-router-dom"
import BarContainer from "../../components/BarContainer/BarContainer"

const ProjectDetails: Page = ({ integration, state, dispatch }) => {
  useLayoutEffect(() => {
    if (state.projectStatus === AppStatus.INITIAL) {
      state.user?.emailAddress && state.user?.projects[0].projectId && dispatch(AppAction.projectLoading())

      state.user?.emailAddress &&
        state.user?.projects[0].projectId &&
        UseCase.loadInitialProject(integration, state.user?.emailAddress, state.user?.projects[0].projectId).then((project) => {
          dispatch(AppAction.projectSuccess(project))
        })
    }
  }, [dispatch, integration, state.projectListStatus, state.projectStatus, state.user])

  function linkIntegration(integrate: string, param: string) {
    let projectid = param
    if (integrate === "Git") {
      window.location.href = "http://localhost:3001"
    } else if (integrate === "Trello") {
      window.location.href = "http://localhost:3002/?projectId=" + projectid
    } else if (integrate === "Google Drives") {
      window.location.href = "http://localhost:3003/update?project_id=" + projectid
    } else if (integrate === "Google Folders") {
      window.location.href = "/googleFolderPage?project_id=" + projectid
    }
  }

  function viewIntegration(integrate: string, param1: string, param2: string) {
    let projectid = param1
    let intid = param2
    if (integrate === "Git") {
      window.location.href = "http://localhost:3001"
    } else if (integrate === "Trello") {
      window.location.href = "http://localhost:3002/?projectId=" + projectid + "&integrationId=" + intid
    } else if (integrate === "Google Drives") {
      window.location.href = "http://localhost:3003/drive?project_id=" + projectid + "&drive_id=" + intid
    } else if (integrate === "Google Folders") {
      window.location.href = "/googleFolderPage?iproject_id=" + projectid + "&intid=" + intid
    }
  }

  // Page Styling
  const classes = useStyles()
  // const [open] = React.useState(true)
  const detailheight = clsx(classes.paper, classes.detailheight)
  const integrationheight = clsx(classes.paper, classes.integrationheight)

  // Tab click handling
  const [value, setValue] = React.useState(0)
  const handleChange = (event: React.ChangeEvent<{}>, newValue: number) => {
    setValue(newValue)
  }

  return (
    <div>
      <BarContainer shouldContainSideBar={true} pageTitle="Dashboard">
        <div>testComponent</div>
      </BarContainer>

      {!state.user?.emailAddress && <Redirect to="/" />}
      {state.projectStatus === AppStatus.LOADING ? (
        <h1>Loading</h1>
      ) : (
        <div className={classes.root}>
          {/* <AppBar position="absolute" color="primary" className={clsx(classes.appBar, !open && classes.appBarShift)}>
              <Toolbar className={classes.toolbar}>
                <Typography component="h1" variant="h6" color="inherit" noWrap className={classes.title}>
                  Project Details
              </Typography>
                <Button type="button" variant="contained" color="default" onClick={() => (window.location.href = "./")}>
                  Back to Project List
              </Button>
              </Toolbar>
            </AppBar> */}
          <main className={classes.content}>
            <div className={classes.appBarSpacer} />
            <Container maxWidth="lg" className={classes.container}>
              <Grid container spacing={3}>
                {/* Project Details */}
                <Grid item xs={12} md={8} lg={7}>
                  <Paper className={detailheight}>
                    <Typography variant="h6" align={"center"} gutterBottom>
                      Project Information
                    </Typography>
                    <Typography variant="body1" gutterBottom>
                      <div>
                        {" "}
                        <strong>Project ID:</strong> {state.currentProject?.projectId}{" "}
                      </div>
                      <div>
                        {" "}
                        <strong>Project Name:</strong> {state.currentProject?.projectName}{" "}
                      </div>
                    </Typography>
                  </Paper>
                </Grid>

                {/* Integration */}
                <Grid item xs={12} md={4} lg={5}>
                  <AppBar position="static">
                    <Tabs value={value} onChange={handleChange} variant="fullWidth" aria-label="Integration Tabs">
                      <Tab label="Google Drive" {...a11yProps(0)} />
                      <Tab label="Git" {...a11yProps(1)} />
                      <Tab label="Trello" {...a11yProps(2)} />
                    </Tabs>
                  </AppBar>
                  <Paper className={integrationheight}>
                    <TabPanel value={value} index={0}>
                      {/* Google */}
                      <Button
                        type="button"
                        variant="contained"
                        color="primary"
                        fullWidth
                        onClick={() => linkIntegration("Google Drives", state.currentProject!.projectId!)}
                      >
                        New Google Drive Integration
                      </Button>
                      <Box p={2} bgcolor="background.paper"></Box>
                      <Typography variant="h6" align={"center"} gutterBottom>
                        Integrated Google Drives
                      </Typography>
                      <Container style={{ maxHeight: 200, padding: 0, overflow: "auto" }}>
                        <List component="nav" aria-label="Google Tab">
                          {state.currentProject?.projectGoogleDriveIds.map((item) => {
                            return (
                              <div>
                                <ListItem button onClick={() => viewIntegration("Google Drives", state.currentProject!.projectId!, item)}>
                                  {item}
                                </ListItem>
                                <Divider />
                              </div>
                            )
                          })}
                        </List>
                      </Container>
                    </TabPanel>
                    <TabPanel value={value} index={1}>
                      {/* Git */}
                      <Button
                        type="button"
                        variant="contained"
                        color="primary"
                        fullWidth
                        onClick={() => linkIntegration("Git", state.currentProject!.projectId!)}
                      >
                        New Git Integration
                      </Button>
                      <Box p={2} bgcolor="background.paper"></Box>
                      <Typography variant="h6" align={"center"} gutterBottom>
                        Integrated Git
                      </Typography>
                      <Container style={{ maxHeight: 200, padding: 0, overflow: "auto" }}>
                        <List component="nav" aria-label="Git Tab">
                          {state.currentProject?.projectGitIds.map((item) => {
                            return (
                              <div>
                                <ListItem button onClick={() => viewIntegration("Git", state.currentProject!.projectId!, item)}>
                                  {item}
                                </ListItem>
                                <Divider />
                              </div>
                            )
                          })}
                        </List>
                      </Container>
                    </TabPanel>
                    <TabPanel value={value} index={2}>
                      {/* Trello */}
                      <Button
                        type="button"
                        variant="contained"
                        color="primary"
                        fullWidth
                        onClick={() => linkIntegration("Trello", state.currentProject!.projectId!)}
                      >
                        New Trello Integration
                      </Button>
                      <Box p={2} bgcolor="background.paper"></Box>
                      <Typography variant="h6" align={"center"} gutterBottom>
                        Integrated Trello
                      </Typography>
                      <Container style={{ maxHeight: 200, padding: 0, overflow: "auto" }}>
                        <List component="nav" aria-label="Trello Tab">
                          {state.currentProject?.projectTrelloIds.map((item) => {
                            return (
                              <div>
                                <ListItem button onClick={() => viewIntegration("Trello", state.currentProject!.projectId!, item)}>
                                  {item}
                                </ListItem>
                                <Divider />
                              </div>
                            )
                          })}
                        </List>
                      </Container>
                    </TabPanel>
                  </Paper>
                </Grid>
              </Grid>
              <Box pt={4}>
                <Copyright />
              </Box>
            </Container>
          </main>
        </div>
      )}
    </div>
  )
}

export default ProjectDetails
