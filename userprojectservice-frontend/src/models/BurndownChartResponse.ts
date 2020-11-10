export interface BurndownChartResponse {
  boardName: string
  listSizes: TrelloMap
}

export interface TrelloMap {
  [trelloKey: string]: CardMap
}

export interface CardMap {
  [cardKey: string]: TrelloCard
}

export interface TrelloCard {
  name: string
  size: number
}
