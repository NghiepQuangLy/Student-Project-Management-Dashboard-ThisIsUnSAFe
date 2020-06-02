import { Integration } from "../integrations/Integration"
import { Dispatch, FunctionComponent } from "react"
import { AppState } from "../state/ProjectListState"
import { AppAction, AppActionType } from "../state/AppAction"

export interface PageProps<T> {
  integration: Integration
  state: T
  dispatch: Dispatch<AppAction<AppActionType, any>>
  children?: never
}

export interface Page<T = AppState> extends FunctionComponent<PageProps<T>> {}
