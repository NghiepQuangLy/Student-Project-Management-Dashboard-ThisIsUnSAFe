import React from "react"
import { Link } from "react-router-dom"

function GoogleFolderPage() {
  return (
    <div>
      <h1>Google Folder Integration</h1>
      <p>Google Folder ID: 00000</p>

      <Link to="/">
        <p>
          <button type="button">Back</button>
        </p>
      </Link>
    </div>
  )
}

export default GoogleFolderPage
