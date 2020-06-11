import React from "react"
import { Link } from "react-router-dom"

function GoogleDocPage() {
  return (
    <div>
      <h1>Google Doc Integration</h1>
      <p>Google Doc ID: 00000</p>

      <Link to="/">
        <p>
          <button type="button">Back</button>
        </p>
      </Link>
    </div>
  )
}

export default GoogleDocPage
