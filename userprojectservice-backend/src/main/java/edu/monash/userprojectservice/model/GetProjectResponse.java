package edu.monash.userprojectservice.model;

import edu.monash.userprojectservice.service.IntegrationObjectResponse;
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
    private List<IntegrationObjectResponse> projectGitIntegration;
    private List<IntegrationObjectResponse> projectGoogleDriveIntegration;
    private List<String> projectGoogleFolderIds;
    private List<IntegrationObjectResponse> projectTrelloIntegration;
}
