import React from "react"
import { Page } from "../Page"
import { Redirect } from "react-router-dom"
import monash from "../../resources/images/M.png"
import GoogleButton from "react-google-button"
import styles from "./Login.module.css"
import { useGoogleAuth } from "../../components/GoogleAuthProvider/GoogleAuthProvider"
import Loading from "../../components/Loading/Loading"

/** This method returns the Login Frontend component which validates the user.
 * @param integration Allows API calls to be used in function
 * @param state The react state of the application
 * @param dispatch The react dispatch of the application
 * @return The HTML for the Login
 */
const Login: Page = ({ integration, state, dispatch }) => {
  const { signIn, googleUser, isInitialized, isSignedIn } = useGoogleAuth()
  const emailAddress = googleUser?.getBasicProfile()?.getEmail()

  // Will only sign in if useGoogleAuth isSignedIn is true
  return (
    <div className={styles.Login}>
      {!isInitialized ? (
        <Loading iconColor={"white"} />
      ) : isSignedIn ? (
        emailAddress ? (
          <Redirect to="/projects" />
        ) : (
          <h1>something went wrong</h1>
        )
      ) : (
        <div className={styles.Rectangle}>
          <h1 className={styles.Welcome}>Welcome to SPMD</h1>
          <img src={monash} alt="profile" className={styles.MonashLogo} />
          <GoogleButton className={styles.GoogleButton} onClick={() => signIn()} />
          
        </div>
      )}
    </div>
  )
}

export default Login
