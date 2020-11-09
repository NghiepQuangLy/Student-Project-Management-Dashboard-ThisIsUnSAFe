import React, { FunctionComponent } from "react"
import styles from "./Dashboard.module.css"
import { ProjectDetail } from "../../state/AppState"
import Table from "@material-ui/core/Table"
import Tooltip from "@material-ui/core/Tooltip"
import HelpOutline from '@material-ui/icons/HelpOutline'
import TableBody from "@material-ui/core/TableBody"
import TableCell from "@material-ui/core/TableCell"
import TableContainer from "@material-ui/core/TableContainer"
import TableRow from "@material-ui/core/TableRow"
import Paper from "@material-ui/core/Paper"
import TableHead from "@material-ui/core/TableHead"
import NotificationsNoneIcon from "@material-ui/icons/NotificationsNone"
import Chart from "react-google-charts"

// Provide an interface for the Dashboard
interface DashboardProps {
  projectDetails?: ProjectDetail
}

 /** This method returns the Dashboard Frontend component which is comprised of the Reminders Table,
  * Trello Progress Chart, Integration Data Table and Moodle Link
  * @param projectDetails The details of the project
  * @return The HTML for the Dashboard
 */
const Dashboard: FunctionComponent<DashboardProps> = ({ projectDetails }) => {
    const distinct_columns = new Set();
    distinct_columns.add("Date")

    // Get distinct columns from burndown chart
    for (let key in projectDetails?.projectBurndownChart?.listSizes) {
        let value = projectDetails?.projectBurndownChart?.listSizes[key];
        for (let key2 in value) {
            let value2 = value[key2];
            distinct_columns.add(value2.name)
        }
    }

    let columns = Array.from( distinct_columns )
    const data = [
        columns
       ];

    // Get row data
    for (let key in projectDetails?.projectBurndownChart?.listSizes) {
        const rowData = []
        for (let i = 0; i < columns.length; i++) {
            rowData.push(0)
        }
        let value = projectDetails?.projectBurndownChart?.listSizes[key];
        rowData[0] = key

        for (let key2 in value) {
            let value2 = value[key2];
            rowData[columns.indexOf(value2.name)] = value2.size
        }
        data.push(rowData)
    }

  return (
    <div className={styles.Dashboard}>
      <div className={styles.Header}>Dashboard</div>
      <div className={styles.Frame}>
        <div className={styles.ReminderHeader}>
          <h2>&ensp;&ensp;Reminders:</h2>
        </div>
          {/*Reminders Table*/}
        <TableContainer component={Paper} className={styles.Container}>
          <Table className={styles.Table} aria-label="Reminders">
            <TableBody>
              {projectDetails?.projectReminderTable.map((data) => (
                <TableRow key={data.reminderName}>
                  <TableCell className={styles.Icon}>
                    <NotificationsNoneIcon />
                  </TableCell>
                  <TableCell component="th" scope="row" className={styles.Rows}>
                    {data.reminderName}
                    <br />
                    {data.reminderProject}
                    <br />
                    {data.reminderDesc}
                  </TableCell>
                  <TableCell align="right">{data.reminderDueDate}</TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
          {/*Trello Progress Chart*/}
        <div className={styles.IntegrationHeader}>
            <h2>&ensp;&ensp;Trello Progress Chart:</h2>
        </div>
        <TableContainer className={styles.Container2}>
        <Chart
            width='90%'
            height='290px'
            chartType="ColumnChart"
            loader={<div>Loading Chart</div>}
            data={data}
            options = {{
                    chartArea: { width: '80%' },
                    legend: { position: 'right', maxLines: 3, alignment:'start' },
                    bar: { groupWidth: '50%' },
                    isStacked: 'percent',
                    vAxes: { 0: {title: 'Card Percentage' }},
                    hAxes: { 0: {title: 'Date' }}
                  }}
            legendToggle
          />
          </TableContainer>
        <span>&nbsp;&nbsp;</span>
        <div className={styles.IntegrationHeader}>
          <h2>Integration History:
            </h2>
        </div>
         {/*Integration Data Table*/}
        <TableContainer component={Paper} className={styles.Container}>
          <Table className={styles.Table} aria-label="customized table">
            <TableHead>
              <TableRow>
                <TableCell className={styles.StyledTableCellHead}>
                Email Address
                <Tooltip
                    title="Enter the Team Drive ID you wish to add to, or remove from, this project."
                    placement="right-end"
                    disableFocusListener
                    disableTouchListener
                    className={styles.Tooltip}
                  ><HelpOutline></HelpOutline></Tooltip>
                </TableCell>
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
          {/*Moodle Link*/}
        <div className={styles.IntegrationHeader}>
          <h2>Moodle Link: </h2>
          <a className={styles.Logo} href={`${projectDetails?.moodleLink}`} />
        </div>
      </div>
    </div>
  )
}

export default Dashboard
