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

import React from 'react';
import { BrowserRouter as Router, Route } from 'react-router-dom';
import { Security, SecureRoute, ImplicitCallback } from '@okta/okta-react';
import { Container } from 'semantic-ui-react';
import Home from './Home';
import Messages from './Messages';
import Navbar from './Navbar';
import Profile from './Profile';
import Projects from './Projects';

const App = () => (
  <Router>
    <Security 
      issuer={`${process.env.OKTA_ORG_URL}/oauth2/default`}
      client_id={process.env.OKTA_CLIENT_ID}
      redirect_uri={`${window.location.origin}/implicit/callback`}>
      <Navbar />
      <Container text style={{ marginTop: '7em' }}>
        <Route path="/" exact component={Home} />
        <Route path="/messages" component={Messages} />
        <Route path="/profile" component={Profile} />
        <Route path="/projects" component={Projects} />
      </Container>
      <Route path="/implicit/callback" component={ImplicitCallback} />
    </Security>
  </Router>
);
export default App;