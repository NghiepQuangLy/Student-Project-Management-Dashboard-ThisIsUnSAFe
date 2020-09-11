import React from "react"
import { Page } from "../Page"
import styles from "./Example.module.css"

const Example: Page = ({ integration, state, dispatch }) => {
  return (
    <div className={styles.Example}>
      <div className={styles.ExampleHeader}>Example Header</div>
      <div className={styles.ExampleCharts}>
        <div className={styles.ExmapleChart}>Git Chart1</div>
        <div className={styles.ExmapleChart}>Git Chart2</div>
        <div className={styles.ExmapleChart}>Git Chart3</div>
      </div>
      <div className={styles.ExmapleContents}>
        <div className={styles.ExmapleContent}>Git Content</div>
      </div>
    </div>
  )
}

export default Example
