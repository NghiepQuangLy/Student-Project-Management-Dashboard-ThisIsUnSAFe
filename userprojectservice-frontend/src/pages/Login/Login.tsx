import React from "react"
import { Page } from "../Page"
import GoogleLogin, { GoogleLoginResponse, GoogleLoginResponseOffline } from "react-google-login"
import * as AppAction from "../../state/AppAction"
import { AppStatus } from "../../models/AppStatus"
import * as UseCase from "../../usecase/UseCase"
import { Redirect } from "react-router-dom"
import monash from "../../resources/images/M.png"
import GoogleButton from "react-google-button"
import styles from "./Login.module.css"
import Loading from "../../components/Loading/Loading"

const Login: Page = ({ integration, state, dispatch }) => {
  const responseGoogle = (response: GoogleLoginResponse | GoogleLoginResponseOffline) => {
    const googleLoginResponse = response as GoogleLoginResponse
    googleLoginResponse && googleLoginResponse.getBasicProfile()
    const emailAddress = googleLoginResponse.getBasicProfile().getEmail()

    // calling sth else when start working on this
    if (state.userStatus === AppStatus.INITIAL && emailAddress) {
      dispatch(AppAction.userLoading())
      UseCase.loadInitialUser(integration, emailAddress).then((user) => {
        dispatch(AppAction.userSuccess(user))
      })
    }
  }

  return (
    <div className={styles.Login}>
      {state.user?.emailAddress && <Redirect to="/Projects" />}
      {state.userStatus === AppStatus.LOADING ? (
        <Loading />
      ) : (
        <div className={styles.Rectangle}>
          <h1 className={styles.Welcome}>Welcome to SPMD</h1>
          <img src={monash} alt="profile" className={styles.MonashLogo} />
          <div className={styles.GoogleButton}>
            <GoogleLogin
              clientId="12178522373-e5nmdu6ogip7e70f2sn645j30n55fgke.apps.googleusercontent.com"
              render={(renderProps) => <GoogleButton onClick={renderProps.onClick} />}
              buttonText="Login"
              onSuccess={responseGoogle}
              onFailure={responseGoogle}
              isSignedIn={true}
              uxMode="redirect"
              redirectUri={process.env.REACT_APP_GOOGLE_REDIRECT_URI}
            />
          </div>
        </div>
      )}
    </div>
  )
}
export default Login
