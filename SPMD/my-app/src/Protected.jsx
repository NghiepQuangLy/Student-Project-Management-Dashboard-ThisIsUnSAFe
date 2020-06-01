// src/Home.jsx

import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import { withOktaAuth } from '@okta/okta-react';
import { Header, Icon, Table } from 'semantic-ui-react';

class Protected extends React.Component {
  state = {projects: null}

  refresh = 0

  componentWillMount = () => {
  	this.setPage();
  }

  setPage = () => {
  	if (this.refresh !== 0) return;
  	this.refresh = 1
  	this.updatePage();
  }

  updatePage = (err) => {
  	if (--this.refresh !== 0) return;

  	this.setState({projects: ["Project 1", "Project 2"]});
  }

  addProject = (current) => {
  	current.push("New Project");
  	this.setState({projects: current});
  	console.log(this.state.projects);
  }

  render() {
    return (
    	<div>
    	<Header as="h1">
          Projects
        </Header>
        <button onClick={() => this.addProject(this.state.projects)}>Add Project</button>
		{this.state.projects.map((project) => <a href="/project"><br/>{project}</a> )}
		</div>
    );
  }
};
export default Protected;