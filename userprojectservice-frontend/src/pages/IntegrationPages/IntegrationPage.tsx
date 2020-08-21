import React, { useLayoutEffect } from "react"
import { Page } from "../Page"
import * as UseCase from "../../usecase/UseCase"
import * as AppAction from "../../state/AppAction"
import { AppStatus } from "../../models/AppStatus"
import BarContainer from "../../components/BarContainer/BarContainer"

const IntegrationPage: Page = ({ integration, state, dispatch }) => {
    useLayoutEffect(() => {
        if (state.projectStatus === AppStatus.INITIAL && state.user?.emailAddress && state.user?.projects[0].projectId) {
            dispatch(AppAction.projectLoading())

            UseCase.loadInitialProject(integration, state.user?.emailAddress, state.user?.projects[0].projectId).then((project) => {
                dispatch(AppAction.projectSuccess(project))
            })
        }
    }, [dispatch, integration, state.projectListStatus, state.projectStatus, state.user])

    return (
        <div>
            <BarContainer shouldContainSideBar={true} pageTitle="Integrations Page" />
            <div>testComponent</div>
        </div>
    )
}

export default IntegrationPage
