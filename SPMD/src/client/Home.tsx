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

import { withAuth } from '@okta/okta-react';
import React, { useState, useEffect } from 'react';
import { Button, Header } from 'semantic-ui-react';
import io from "socket.io-client";
  
import { useAuth } from "./auth";
import MessageList from "./MessageList";
import Projects from './Projects';
import NewMessage from "./NewMessage";

const Home = withAuth(({ auth }) => {
  const [authenticated, user, token] = useAuth(auth);

  return (
    <div>
      {user ? (
      <div>
        <Header as="h1">Welcome {user.name}</Header>
        <button onClick={() => auth.logout()}>Sign out</button>
      </div>
      ) : (
      <div>
        Not signed in
        <button onClick={() => auth.login()}>Sign in</button>
      </div>
      )}
    </div>
  );
};
export default Home;