import React from "react"
import "./Login.css"
import { Page } from "../Page"
import { GoogleLoginButton } from 'ts-react-google-login-component';

const responseGoogle(googleUser: window.gapi.auth2.GooglUser) => {
  console.log(response);
  this.history.push("./project");
}
// const preLoginTracking() => {
//   console.log('Attemp to login with google');
// }
//
// const errorHandler(error: string) => {
//   // handle error if login got failed...
//   console.error(error);
// }
//
// const responseGoogle(googleUser: gapi.auth2.GoogleUser) => {
//   const id_token = googleUser.getAuthResponse(true).id_token
//   const googleId = googleUser.getId()
//
//   console.log({ googleId })
//   console.log({accessToken: id_token})
//   // Make user login in your system
//   // login success tracking...
// }
const Login: Page = ({ integration, state, dispatch }) => {
  return (
    <GoogleLogin
      clientId="658977310896-knrl3gka66fldh83dao2rhgbblmd4un9.apps.googleusercontent.com"
      buttonText="Login"
      onSuccess={responseGoogle}
      onFailure={responseGoogle}
      cookiePolicy={"single_host_origin"}
    />
  )
}

export default Login
