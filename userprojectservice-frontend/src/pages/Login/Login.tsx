import React from "react"
import "./Login.css"
import { Page } from "../Page"
import { GoogleLoginButton } from 'ts-react-google-login-component';

// const responseGoogle(googleUser: window.gapi.auth2.GooglUser) => {
//   console.log(response);
//   this.history.push("./project");
//}
const preLoginTracking = () => {
  console.log('Attemp to login with google');
}

const errorHandler = (error: string) => {
  // handle error if login got failed...
  console.error(error);
}

const responseGoogle = (googleUser: gapi.auth2.GoogleUser) => {
  const id_token = googleUser.getAuthResponse(true).id_token
  const googleId = googleUser.getId()

  console.log({ googleId })
  console.log({accessToken: id_token})
  // Make user login in your system
  // login success tracking...
}

const Login: Page = ({ integration, state, dispatch }) => {
  const clientConfig = { client_id: '12178522373-cd2s7387k0kjtqf3tebfoahk82m6prt9.apps.googleusercontent.com' }
  return (
      <div>
        <GoogleLoginButton
            responseHandler={responseGoogle}
            clientConfig={clientConfig}
            preLogin={preLoginTracking}
            failureHandler={errorHandler}
        />
      </div>
  )
}

export default Login
