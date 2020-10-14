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
    private String projectUnitCode;
    private Integer projectYear;
    private String projectSemester;
    private String projectTimesheet;
    private List<IntegrationObjectResponse> projectGitIntegration;
    private List<IntegrationObjectResponse> projectGoogleDriveIntegration;
    private List<IntegrationObjectResponse> projectTrelloIntegration;
    private List<IntegrationTableObjectResponse> projectIntegrationTable;
}
