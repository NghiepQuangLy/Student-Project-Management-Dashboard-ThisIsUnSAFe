import React, {FunctionComponent} from "react"
import styles from "./Dashboard.module.css"
import {ProjectDetail} from "../../state/AppState";


import { withStyles} from '@material-ui/core/styles';
import Table from "@material-ui/core/Table";
import TableBody from "@material-ui/core/TableBody";
import TableCell from "@material-ui/core/TableCell";
import TableContainer from "@material-ui/core/TableContainer";
import TableRow from "@material-ui/core/TableRow";
import Paper from "@material-ui/core/Paper";
import TableHead from '@material-ui/core/TableHead';
import NotificationsNoneIcon from '@material-ui/icons/NotificationsNone';

interface DashboardProps {
    projectDetails?: ProjectDetail
}

// Integration Table
const StyledTableCell = withStyles((theme) => ({
    head: {
        backgroundColor: theme.palette.common.black,
        color: theme.palette.common.white,
    },
    body: {
        fontSize: 14,
    },
}))(TableCell);

const StyledTableRow = withStyles((theme) => ({
    root: {
        '&:nth-of-type(odd)': {
            backgroundColor: theme.palette.action.hover,
        },
    },
}))(TableRow);

const Dashboard: FunctionComponent<DashboardProps> = ({projectDetails}) => {
    return (
        <div className={styles.Dashboard}>
            <div className={styles.Header}>Dashboard</div>
            <div className={styles.Frame}>
                <div className={styles.Header2}>
                    <h2>Reminders:</h2>
                </div>
                <TableContainer component={Paper} className={styles.Container}>
                    <Table className={styles.Table} aria-label="Reminders">
                        <TableBody>
                            {projectDetails?.projectReminderTable.map((data) => (
                                <TableRow key={data.reminderActivity}>
                                    <TableCell className={styles.Icon}><NotificationsNoneIcon></NotificationsNoneIcon></TableCell>
                                    <TableCell component="th" scope="row"
                                               className={styles.Rows}>
                                        {data.reminderActivity}<br></br>{data.reminderUnitCode} {data.reminderUnitName}
                                    </TableCell>
                                    <TableCell align="right">{data.reminderDate}</TableCell>
                                    <TableCell align="right">{data.reminderTime}</TableCell>
                                </TableRow>
                            ))}
                        </TableBody>
                    </Table>
                </TableContainer>
                <span>&nbsp;&nbsp;</span>
                <div className={styles.Header2}>
                    <h2>Integration History:</h2>
                </div>
                <TableContainer component={Paper} className={styles.Container}>
                    <Table className={styles.Table} aria-label="customized table">
                        <TableHead>
                            <TableRow>
                                <StyledTableCell>Email Address</StyledTableCell>
                                <StyledTableCell align="left">Git</StyledTableCell>
                                <StyledTableCell align="left">Google Drive</StyledTableCell>
                                <StyledTableCell align="left">Trello</StyledTableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {projectDetails?.projectIntegrationTable.map((data) => (
                                <StyledTableRow key={data.emailAddress}>
                                    <StyledTableCell component="th" scope="row">
                                        {data.emailAddress}
                                    </StyledTableCell>
                                    <StyledTableCell align="left">{data.gitIntegrationLastModified}</StyledTableCell>
                                    <StyledTableCell align="left">{data.googleDriveIntegrationLastModified}</StyledTableCell>
                                    <StyledTableCell align="left">{data.trelloIntegrationLastModified}</StyledTableCell>
                                </StyledTableRow>
                            ))}
                        </TableBody>
                    </Table>
                </TableContainer>
            </div>
            <div>
                <div>{projectDetails?.moodleLink}</div>
            </div>
        </div>
    )
}

export default Dashboard
