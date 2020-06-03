import React from "react";
import TrelloIntegration from './components/TrelloIntegration';
import GitIntegration from './components/GitIntegration';
import GDocIntegration from './components/GDocIntegration';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import { Link } from 'react-router-dom';

function App() {
  return (
    <Router>
      <Switch>
        <Route path="/" exact component={Home} />
        <Route path="/GitIntegration" component={GitIntegration} />
        <Route path="/TrelloIntegration" component={TrelloIntegration} />
        <Route path="/GDocIntegration" component={GDocIntegration} />
      </Switch>
    </Router>
  );
}

const Home = () => (
  <div>
    <h1>Project Name: ThisIsUnSafe </h1>
    <Link to="/TrelloIntegration">
      <p><button type="button">Trello Integration</button></p>
    </Link>
    <Link to="/GDocIntegration">
      <p><button type="button">Google Doc Integration</button></p>
    </Link>
    <Link to="/GitIntegration">
      <p><button type="button">Git Integration</button></p>
    </Link>
  </div>
)

export default App;
