/*
 * Copyright (c) 2018, Okta, Inc. and/or its affiliates. All rights reserved.
 * The Okta software accompanied by this notice is provided pursuant to the Apache License, Version 2.0 (the "License.")
 *
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0.
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 * See the License for the specific language governing permissions and limitations under the License.
 */

import React, { useState, useEffect } from 'react';
import { withAuth } from '@okta/okta-react';
import { Header, Icon, Table } from 'semantic-ui-react';

import { useAuth } from "./auth";


const Projects = withAuth(({ auth }) => {
  const [authenticated, user, token] = useAuth(auth);
  let projects = ["Project 1", "Project 2"];

  if (!user) {
    return (
      <div>
        <p>Fetching user profile...</p>
      </div>
    );
  }

  function addProject() {
  	projects.push("New Project");
  	console.log(projects);
  }

  return (
    <div>
      <div>
        <Header as="h1">
          {user.name}'s Projects
        </Header>
        <button onClick={() => addProject()}>Add Project (See console)</button>
		{projects.map((project) => <a href="/project"><br/>{project}</a> )}
      </div>
    </div>
  );
};
export default Projects;