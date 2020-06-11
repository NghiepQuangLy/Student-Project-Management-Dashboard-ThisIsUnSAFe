import React from "react"
import { Link } from "react-router-dom"

function TrelloPage() {
  return (
    <div>
      <h1>Trello Integration</h1>
      <p>Trello ID: abcde</p>
      <Link to="/">
        <p>
          <button type="button">Back</button>
        </p>
      </Link>
    </div>
  )
}

export default TrelloPage
