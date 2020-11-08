import { Integration } from "../integrations/Integration"
import { Dispatch, FunctionComponent } from "react"
import { AppState } from "../state/AppState"
import { AppAction, AppActionType } from "../state/AppAction"

// Provide PageProps interface
export interface PageProps<T> {
  integration: Integration
  state: T
  dispatch: Dispatch<AppAction<AppActionType, any>>
  children?: never
}

export interface Page<T = AppState> extends FunctionComponent<PageProps<T>> {}
