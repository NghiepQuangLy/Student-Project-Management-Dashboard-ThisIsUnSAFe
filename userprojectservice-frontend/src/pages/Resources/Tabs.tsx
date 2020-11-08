import React from "react"
import Typography from "@material-ui/core/Typography"
import Box from "@material-ui/core/Box"

interface TabPanelProps {
  children?: React.ReactNode
  index: any
  value: any
}

/** This method returns the Tab Panel component which provides the tabs for the three integrations on the
 * Project Details Landing page
 * @param props Provides the chilrdren, value, index and ...other.
 * @return The HTML for the TabPanel
 */
export default function TabPanel(props: TabPanelProps) {
  const { children, value, index, ...other } = props

  return (
    <div role="tabpanel" hidden={value !== index} id={`simple-tabpanel-${index}`} aria-labelledby={`simple-tab-${index}`} {...other}>
      {value === index && (
        <Box p={3}>
          <Typography>{children}</Typography>
        </Box>
      )}
    </div>
  )
}

export function a11yProps(index: any) {
  return {
    id: `simple-tab-${index}`,
    "aria-controls": `simple-tabpanel-${index}`
  }
}
