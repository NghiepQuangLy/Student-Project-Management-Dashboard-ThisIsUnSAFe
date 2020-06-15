import React from "react"
import { Link } from "react-router-dom"

function GoogleDrivePage() {
  return (
    <div>
      <h1>Google Drive Integration</h1>
      <p>Google Drive ID: 00000</p>

      <Link to="/">
        <p>
          <button type="button">Back</button>
        </p>
      </Link>
    </div>
  )
}

export default GoogleDrivePage
