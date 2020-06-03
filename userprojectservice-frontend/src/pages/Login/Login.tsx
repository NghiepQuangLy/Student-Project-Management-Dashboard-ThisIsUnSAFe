import React from "react"
import "./Login.css"
import { Page } from "../Page"
import GoogleLogin, { GoogleLoginResponse, GoogleLoginResponseOffline } from "react-google-login"
import * as AppAction from "../../state/AppAction"

const Login: Page = ({ integration, state, dispatch }) => {
  const responseGoogle = (response: GoogleLoginResponse | GoogleLoginResponseOffline) => {
    console.log(response)
    const test = response as GoogleLoginResponse
    test && test.getBasicProfile()
    console.log(test.getBasicProfile().getEmail())

    // calling sth else when start working on this
    dispatch(AppAction.projectListLoading())
  }

  console.log(process.env.REACT_APP_GOOGLE_REDIRECT_URI)

  return (
    <div>
      <GoogleLogin
        clientId="12178522373-e5nmdu6ogip7e70f2sn645j30n55fgke.apps.googleusercontent.com"
        buttonText="Login"
        onSuccess={responseGoogle}
        onFailure={responseGoogle}
        isSignedIn={true}
        uxMode="redirect"
        redirectUri={process.env.REACT_APP_GOOGLE_REDIRECT_URI}
      />
    </div>
  )
}

export default Login
