import React from "react"
import { Page } from "../Page"
import GoogleLogin, { GoogleLoginResponse, GoogleLoginResponseOffline } from "react-google-login"
import * as AppAction from "../../state/AppAction"
import { AppStatus } from "../../models/AppStatus"
import * as UseCase from "../../usecase/UseCase"
import { Redirect } from "react-router-dom"
import monash from '../../resources/images/M.png'
import GoogleButton from "react-google-button"
import styles from "./Login.module.css"

const Login: Page = ({ integration, state, dispatch }) => {
  const responseGoogle = (response: GoogleLoginResponse | GoogleLoginResponseOffline) => {
    const googleLoginResponse = response as GoogleLoginResponse
    googleLoginResponse && googleLoginResponse.getBasicProfile()
    const emailAddress = googleLoginResponse.getBasicProfile().getEmail()

    // calling sth else when start working on this
    if (state.userStatus === AppStatus.INITIAL) {
      dispatch(AppAction.userLoading())
      if (emailAddress) {
      }
      UseCase.loadInitialUser(integration, emailAddress).then((user) => {
        dispatch(AppAction.userSuccess(user))
      })
    }
  }

  return (
    <div className={styles.Login}>
      {state.user?.emailAddress && <Redirect to="/Projects" />}
      <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh' }}>
        <div className={styles.rectangle}>
          <div className={styles.rectangleComponents}>
            <h1 className={styles.welcome} >Welcome to SPMD</h1>
            <br />
            <img src={monash} alt='profile' className={styles.monashLogo} />
            <br />
            <br />
            <br />
            <br />
            <div className={styles.googleButton}>
              <GoogleLogin
                clientId="12178522373-e5nmdu6ogip7e70f2sn645j30n55fgke.apps.googleusercontent.com"
                render={renderProps => (
                  <GoogleButton onClick={renderProps.onClick}
                  />
                )}
                buttonText="Login"
                onSuccess={responseGoogle}
                onFailure={responseGoogle}
              />
            </div>
          </div>
        </div>
      </div >
    </div>
  )
}
export default Login

