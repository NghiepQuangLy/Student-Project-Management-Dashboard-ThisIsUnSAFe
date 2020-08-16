import React, { useLayoutEffect } from "react"
import "./ProjectList.module.css"
import { Page } from "../Page"
import { Link, Redirect } from "react-router-dom"
import * as UseCase from "../../usecase/UseCase"
import * as AppAction from "../../state/AppAction"
import { AppStatus } from "../../models/AppStatus"
import clsx from "clsx"
import Typography from "@material-ui/core/Typography"
import Container from "@material-ui/core/Container"
import { Grid, List } from "@material-ui/core"
import Paper from "@material-ui/core/Paper"
import Box from "@material-ui/core/Box"
import Copyright, { useStyles } from "../Resources/Styles"
import BarContainer from "../../components/BarContainer/BarContainer"

const ProjectList: Page = ({ integration, state, dispatch }) => {
  const isEmpty = state.projectListStatus === AppStatus.SUCCESS && state.user?.projects.length === 0

  useLayoutEffect(() => {
    if (state.projectStatus === AppStatus.INITIAL && state.user?.emailAddress && state.user?.projects[0].projectId) {
      dispatch(AppAction.projectLoading())

      UseCase.loadInitialProject(integration, state.user?.emailAddress, state.user?.projects[0].projectId).then((project) => {
        dispatch(AppAction.projectSuccess(project))
      })
    }
  }, [dispatch, integration, state.projectListStatus, state.projectStatus, state.user])

  // Page Styling
  const classes = useStyles()
  const userdetailheight = clsx(classes.paper, classes.userdetailheight)

  return (
    <div>
      <BarContainer shouldContainSideBar={false} pageTitle="Project List">
        <div>testComponent</div>
      </BarContainer>

      {!state.user?.emailAddress && <Redirect to="/" />}
      <div>
        {state.userStatus === AppStatus.LOADING ? (
          <h1>Loading</h1>
        ) : isEmpty ? (
          <h1>Empty History</h1>
        ) : (
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
                        Project Information
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
                          <strong>Email Address:</strong> {state.user?.emailAddress}{" "}
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
                        {state.projectListStatus === AppStatus.LOADING ? (
                          <h1>Loading</h1>
                        ) : isEmpty ? (
                          <h1>Empty History</h1>
                        ) : (
                          <List component="nav" aria-label="Trello Tab">
                            {state.user &&
                              state.user.projects.map((item) => {
                                return (
                                  <Link key={item.projectId} to={{ pathname: "/project", state: { from: "item.projectId" } }}>
                                    {"projectId: " + item.projectId + "|projectName:" + item.projectName}
                                    <br />
                                  </Link>
                                )
                              })}
                          </List>
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
        )}
      </div>
    </div>
  )
}

export default ProjectList
