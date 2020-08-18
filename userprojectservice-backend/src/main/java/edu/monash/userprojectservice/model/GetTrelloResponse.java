package edu.monash.userprojectservice.model;

import edu.monash.userprojectservice.repository.trello.TrelloEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetTrelloResponse {
    private List<TrelloEntity> trelloId;
}
