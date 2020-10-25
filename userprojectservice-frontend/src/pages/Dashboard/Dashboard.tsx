import React, { FunctionComponent } from "react"
import styles from "./Dashboard.module.css"
import { ProjectDetail } from "../../state/AppState"
import Table from "@material-ui/core/Table"
import TableBody from "@material-ui/core/TableBody"
import TableCell from "@material-ui/core/TableCell"
import TableContainer from "@material-ui/core/TableContainer"
import TableRow from "@material-ui/core/TableRow"
import Paper from "@material-ui/core/Paper"
import TableHead from "@material-ui/core/TableHead"
import NotificationsNoneIcon from "@material-ui/icons/NotificationsNone"

interface DashboardProps {
  projectDetails?: ProjectDetail
}

const Dashboard: FunctionComponent<DashboardProps> = ({ projectDetails }) => {
  return (
    <div className={styles.Dashboard}>
      <div className={styles.Header}>Dashboard</div>
      <div className={styles.Frame}>
        <div className={styles.ReminderHeader}>
          <h2>&ensp;&ensp;Reminders:</h2>
        </div>
        <TableContainer component={Paper} className={styles.Container}>
          <Table className={styles.Table} aria-label="Reminders">
            <TableBody>
              {projectDetails?.projectReminderTable.map((data) => (
                <TableRow key={data.reminderActivity}>
                  <TableCell className={styles.Icon}>
                    <NotificationsNoneIcon />
                  </TableCell>
                  <TableCell component="th" scope="row" className={styles.Rows}>
                    {data.reminderActivity}
                    <br />
                    {data.reminderUnitCode} {data.reminderUnitName}
                  </TableCell>
                  <TableCell align="right">{data.reminderDate}</TableCell>
                  <TableCell align="right">{data.reminderTime}</TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
        <span>&nbsp;&nbsp;</span>
        <div className={styles.IntegrationHeader}>
          <h2>Integration History:</h2>
        </div>
        <TableContainer component={Paper} className={styles.Container}>
          <Table className={styles.Table} aria-label="customized table">
            <TableHead>
              <TableRow>
                <TableCell className={styles.StyledTableCellHead}>Email Address</TableCell>
                <TableCell className={styles.StyledTableCellHead} align="left">
                  Git
                </TableCell>
                <TableCell className={styles.StyledTableCellHead} align="left">
                  Google Drive
                </TableCell>
                <TableCell className={styles.StyledTableCellHead} align="left">
                  Trello
                </TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {projectDetails?.projectIntegrationTable.map((data) => (
                <TableRow className={styles.StyledTableRow} key={data.emailAddress}>
                  <TableCell component="th" scope="row">
                    {data.emailAddress}
                  </TableCell>
                  <TableCell align="left">{data.gitIntegrationLastModified}</TableCell>
                  <TableCell align="left">{data.googleDriveIntegrationLastModified}</TableCell>
                  <TableCell align="left">{data.trelloIntegrationLastModified}</TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
        <div className={styles.IntegrationHeader}>
          <h2>Moodle Link: </h2>
          <a className={styles.Logo} href={`${projectDetails?.moodleLink}`} />
        </div>
      </div>
    </div>
  )
}

export default Dashboard
