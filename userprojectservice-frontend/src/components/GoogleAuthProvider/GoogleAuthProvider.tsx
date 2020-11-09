import { useGoogleLogin } from "react-use-googlelogin"
import React, { FunctionComponent } from "react"
import { HookReturnValue } from "react-use-googlelogin/dist/types"

/**
 * This method returns the GoogleAuthProvider component which is a context provider for all its children components
 * @param children The children components that will / may use the context provided by the GoogleAuthProvider component
 * @return The HTML for the GoogleAuthProvider component
 */
const GoogleAuthProvider: FunctionComponent = ({ children }) => {
  // google login configuration
  const googleAuth = useGoogleLogin({
    clientId: "12178522373-e5nmdu6ogip7e70f2sn645j30n55fgke.apps.googleusercontent.com",
    persist: true,
    fetchBasicProfile: true,
    uxMode: "redirect",
    redirectUri: process.env.REACT_APP_GOOGLE_REDIRECT_URI
  })

  // use provider to store the googleAuth
  return <GoogleAuthContext.Provider value={googleAuth}>{children}</GoogleAuthContext.Provider>
}

export const GoogleAuthContext = React.createContext({} as HookReturnValue)

/**
 * This method gets googleAuth stored in Provider
 */
export const useGoogleAuth = () => React.useContext(GoogleAuthContext)

export default GoogleAuthProvider
