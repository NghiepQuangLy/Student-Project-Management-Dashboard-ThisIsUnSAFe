package edu.monash.userprojectservice.model;

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
    private List<String> projectGitIds;
    private List<String> projectGoogleDriveIds;;
    private List<String> projectGoogleFolderIds;
    private List<String> projectTrelloIds;
}
