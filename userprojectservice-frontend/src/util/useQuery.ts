import { useLocation } from "react-router-dom"

export const PROJECT_ID_QUERY = "project-id"
export const TRELLO_ID_QUERY = "trello-id"

export const useQuery = () => new URLSearchParams(useLocation().search)
