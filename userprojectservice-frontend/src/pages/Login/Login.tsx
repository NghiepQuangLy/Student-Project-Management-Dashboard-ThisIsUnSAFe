import React from "react"
import "./Login.css"
import { Page } from "../Page"
import GoogleLogin, { GoogleLoginResponse, GoogleLoginResponseOffline } from "react-google-login"
import * as AppAction from "../../state/AppAction"
import { AppStatus } from "../../models/AppStatus"
import * as UseCase from "../../usecase/UseCase"

const Login: Page = ({ integration, state, dispatch }) => {
  const responseGoogle = (response: GoogleLoginResponse | GoogleLoginResponseOffline) => {
    console.log(response)
    const googleLoginResponse = response as GoogleLoginResponse
    googleLoginResponse && googleLoginResponse.getBasicProfile()
    const emailAddress = googleLoginResponse.getBasicProfile().getEmail()
    console.log(emailAddress)

    // calling sth else when start working on this
    if (state.userStatus === AppStatus.INITIAL) {
      dispatch(AppAction.userLoading())

      UseCase.loadInitialUser(integration, emailAddress).then((user) => {
        dispatch(AppAction.userSuccess(user))
      })
    }
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
