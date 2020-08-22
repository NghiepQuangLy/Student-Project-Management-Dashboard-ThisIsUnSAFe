import { useGoogleLogin } from "react-use-googlelogin"
import React, { FunctionComponent } from "react"
import { HookReturnValue } from "react-use-googlelogin/dist/types"

export const GoogleAuthContext = React.createContext({} as HookReturnValue)

const GoogleAuthProvider: FunctionComponent = ({ children }) => {
  const googleAuth = useGoogleLogin({
    clientId: "12178522373-e5nmdu6ogip7e70f2sn645j30n55fgke.apps.googleusercontent.com",
    persist: true,
    fetchBasicProfile: true,
    uxMode: "redirect",
    redirectUri: process.env.REACT_APP_GOOGLE_REDIRECT_URI
  })

  const { googleUser, isInitialized } = googleAuth
  const emailAddress = googleUser?.getBasicProfile()?.getEmail()
  console.log(isInitialized)
  console.log(emailAddress)

  return <GoogleAuthContext.Provider value={googleAuth}>{children}</GoogleAuthContext.Provider>
}

export const useGoogleAuth = () => React.useContext(GoogleAuthContext)

export default GoogleAuthProvider
