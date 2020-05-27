package edu.monash.userprojectservice.model;

import edu.monash.userprojectservice.repository.Git;
import edu.monash.userprojectservice.repository.GoogleDoc;
import edu.monash.userprojectservice.repository.Trello;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetProjectResponse {
    private String projectId;
    private String projectName;
    private List<Git> projectGitIds;
    private List<GoogleDoc> projectGoogleDocIds;
    private List<Trello> projectTrelloIds;
}
