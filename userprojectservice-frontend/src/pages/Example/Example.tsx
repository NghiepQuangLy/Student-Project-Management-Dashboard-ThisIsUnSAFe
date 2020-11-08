import React from "react"
import { Page } from "../Page"
import styles from "./Example.module.css"
import BarContainer from "../../components/BarContainer/BarContainer";

const Example: Page = ({ integration, state, dispatch }) => {
  return (
      <BarContainer shouldContainSideBar={false}>
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
      </BarContainer>
  )
}

export default Example
