import React, { FunctionComponent } from "react"
import styles from "./Example.module.css"
import { ProjectDetail } from "../../state/AppState"

import { withStyles, makeStyles } from "@material-ui/core/styles"
import Table from "@material-ui/core/Table"
import TableBody from "@material-ui/core/TableBody"
import TableCell from "@material-ui/core/TableCell"
import TableContainer from "@material-ui/core/TableContainer"
import TableRow from "@material-ui/core/TableRow"
import Paper from "@material-ui/core/Paper"
import TableHead from "@material-ui/core/TableHead"
import NotificationsNoneIcon from "@material-ui/icons/NotificationsNone"

interface ExampleDashboardProps {
  projectDetails?: ProjectDetail
}

// Reminder
const useStyles = makeStyles({
  table: {
    minWidth: 650
  },
  rows: {
    display: "flex",
    alignItems: "center",
    paddingLeft: "0px"
  },
  icon: {
    alignItems: "center",
    paddingRight: "0px"
  }
})

function createDataReminder(activity: string, unitCode: string, unitName: string, date: string, time: string) {
  return { activity, unitCode, unitName, date, time }
}

const rowsReminder = [
  createDataReminder("Frozen yoghurt", "FIT3170", "Software Engineering Practice", "Jan 1st", "12:30 PM"),
  createDataReminder("Ice cream sandwich", "FIT3170", "Software Engineering Practice", "Jan 12t", "6:20 PM"),
  createDataReminder("Eclair", "FIT3171", "Databases", "Feb 1st", "3:40 PM"),
  createDataReminder("Cupcake", "FIT3170", "Software Engineering Practice", "Dec 1st", "2:30 PM"),
  createDataReminder("Gingerbread", "FIT3171", "Databases", "Nov 1st", "1:30 PM")
]

// Integration Table
const StyledTableCell = withStyles((theme) => ({
  head: {
    backgroundColor: theme.palette.common.black,
    color: theme.palette.common.white
  },
  body: {
    fontSize: 14
  }
}))(TableCell)

const StyledTableRow = withStyles((theme) => ({
  root: {
    "&:nth-of-type(odd)": {
      backgroundColor: theme.palette.action.hover
    }
  }
}))(TableRow)

function createDataIntegrationTable(emailAddress: string, trelloId: string, gitId: string, googleId: string) {
  return { emailAddress: emailAddress, trelloId: trelloId, gitId: gitId, googleId: googleId }
}

const rowsIntegrationTable = [
  createDataIntegrationTable("John@student.monash.edu", "12 Oct 2020", "9 Dec 2020", "5 Dec 2020"),
  createDataIntegrationTable("Adam@student.monash.edu", "1 Dec 2020", "7 Oct 2020", "31 Dec 2020"),
  createDataIntegrationTable("Sarah@student.monash.edu", "12 Nov 2020", "3 Dec 2020", "23 Oct 2020"),
  createDataIntegrationTable("Lily@student.monash.edu", "6 Oct 2020", "10 Dec 2020", "31 Dec 2020"),
  createDataIntegrationTable("Mike@student.monash.edu", "12 Nov 2020", "23 Oct 2020", "1 Nov 2020")
]

const ExampleDashboard: FunctionComponent<ExampleDashboardProps> = ({ projectDetails }) => {
  const classes = useStyles()
  return (
    <div className={styles.Example}>
      <div className={styles.ExampleHeader}>Dashboard</div>
      <div className={styles.ExampleCharts}>
        <TableContainer component={Paper}>
          <Table className={classes.table} aria-label="Reminders">
            <TableBody>
              {rowsReminder.map((row) => (
                <TableRow key={row.activity}>
                  <TableCell className={classes.icon}>
                    <NotificationsNoneIcon></NotificationsNoneIcon>
                  </TableCell>
                  <TableCell component="th" scope="row" className={classes.rows}>
                    {row.activity}
                    <br></br>
                    {row.unitCode} {row.unitName}
                  </TableCell>
                  <TableCell align="right">{row.date}</TableCell>
                  <TableCell align="right">{row.time}</TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>

        <TableContainer component={Paper}>
          <Table className={classes.table} aria-label="customized table">
            <TableHead>
              <TableRow>
                <StyledTableCell>Email Address</StyledTableCell>
                <StyledTableCell align="left">Trello</StyledTableCell>
                <StyledTableCell align="left">Git</StyledTableCell>
                <StyledTableCell align="left">Google Drive</StyledTableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {rowsIntegrationTable.map((row) => (
                <StyledTableRow key={row.emailAddress}>
                  <StyledTableCell component="th" scope="row">
                    {row.emailAddress}
                  </StyledTableCell>
                  <StyledTableCell align="left">{row.trelloId}</StyledTableCell>
                  <StyledTableCell align="left">{row.gitId}</StyledTableCell>
                  <StyledTableCell align="left">{row.googleId}</StyledTableCell>
                </StyledTableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
        <div className={styles.ExmapleChart}>Chart1</div>
        <div className={styles.ExmapleChart}>Chart2</div>
        <div className={styles.ExmapleChart}>Chart3</div>
      </div>
      <div className={styles.ExmapleContents}>
        <div className={styles.ExmapleContent}>{projectDetails?.moodleLink}</div>
      </div>
    </div>
  )
}

export default ExampleDashboard
