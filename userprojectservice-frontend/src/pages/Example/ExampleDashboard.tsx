import React, { FunctionComponent } from "react"
import styles from "./Example.module.css"


const ExampleDashboard: FunctionComponent = () => {
    return (
        <div className={styles.Example}>
            <div className={styles.ExampleHeader}>Dashboard</div>
            <div className={styles.ExampleCharts}>
                <div className={styles.ExmapleChart}>Chart1</div>
                <div className={styles.ExmapleChart}>Chart2</div>
                <div className={styles.ExmapleChart}>Chart3</div>
            </div>
            <div className={styles.ExmapleContents}>
                <div className={styles.ExmapleContent}>Timesheet</div>
            </div>
        </div>
    )
}

export default ExampleDashboard
