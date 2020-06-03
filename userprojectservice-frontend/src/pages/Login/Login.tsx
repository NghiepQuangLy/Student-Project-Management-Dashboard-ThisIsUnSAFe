import React from "react"
import "./Login.css"
import { Page } from "../Page"
import { GoogleLoginButton } from "ts-react-google-login-component"

const preLoginTracking = () => {
  console.log("Attemp to login with google")
}

const errorHandler = (error: string) => {
  console.error(error)
}

const responseGoogle = (googleUser: gapi.auth2.GoogleUser) => {
  const id_token = googleUser.getAuthResponse(true).id_token
  const googleId = googleUser.getId()

  console.log({ googleId })
  console.log(googleUser.getBasicProfile().getEmail())
  console.log({ accessToken: id_token })
}

export type uxMode = "redirect" | "popup" | undefined

const Login: Page = ({ integration, state, dispatch }) => {
  const clientConfig = {
    client_id: "12178522373-cd2s7387k0kjtqf3tebfoahk82m6prt9.apps.googleusercontent.com",
    ux_mode: "redirect" as uxMode,
    redirect_uri: process.env.REACT_APP_GOOGLE_REDIRECT_URI
  }

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
