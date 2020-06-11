import React from "react"
import { Link } from "react-router-dom"

export default function GitPage() {
  return (
    <div>
      <h1>Git Integration</h1>
      <p>Git ID: 12345</p>
      <Link to="/">
        <p>
          <button type="button">Back</button>
        </p>
      </Link>
    </div>
  )
}
